package module

import controllers.{AssetsComponents, HomeController}
import monix.execution.Scheduler
import play.api._
import play.api.libs.ws.ahc.AhcWSClient
import play.api.mvc.{ControllerComponents, EssentialFilter}
import play.filters.cors.{CORSConfig, CORSFilter}
import play.filters.gzip.{GzipFilter, GzipFilterConfig}
import play.filters.hosts.{AllowedHostsConfig, AllowedHostsFilter}
import router.MainRouter

import scala.concurrent.Future

class MainModule(context: ApplicationLoader.Context) extends BuiltInComponentsFromContext(context)
  with AssetsComponents {

  val corsFilter = new CORSFilter(CORSConfig.fromConfiguration(configuration))
  val gzipFilter = new GzipFilter(GzipFilterConfig.fromConfiguration(configuration))

  val allowedHostsFilter = AllowedHostsFilter(
    AllowedHostsConfig.fromConfiguration(configuration),
    httpErrorHandler
  )

  def httpFilters: Seq[EssentialFilter] = Seq(
    allowedHostsFilter,
    corsFilter,
    gzipFilter
  )

  override implicit lazy val executionContext: Scheduler = monix.execution.Scheduler.Implicits.global
  implicit val implicitEnvironment: Environment = environment
  implicit val implicitControllerComponents: ControllerComponents = controllerComponents


  val wsClient = AhcWSClient()

  val homeController = new HomeController()

  override val router = new MainRouter(homeController, assets)

  applicationLifecycle.addStopHook { () =>
    for {
      _ <- Future(wsClient.close())
    } yield ()
  }
}
