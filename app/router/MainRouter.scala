package router


import controllers.{Assets, HomeController}
import play.api.routing.{Router, SimpleRouter}
import play.api.routing.sird._

class MainRouter(
  homeController: HomeController,
  assetsController: Assets) extends SimpleRouter {
  def routes: Router.Routes = {

    case GET(p"/") => homeController.index

    // static resources
    case GET(p"/assets/$file*") =>
      assetsController.versioned(path = "/public", file)

    case GET(p"/$file*") =>
      assetsController.versioned(path = "/public", file)
  }

}
