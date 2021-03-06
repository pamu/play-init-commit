package providers

import module.MainModule
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.components.OneAppPerSuiteWithComponents
import play.api.http.{HeaderNames, Status}
import play.api.test.ResultExtractors
import play.api.{ApplicationLoader, Environment, Mode}

trait PlayPerSuiteProvider
  extends OneAppPerSuiteWithComponents
    with HeaderNames
    with Status
    with ResultExtractors { self: PlaySpec =>

  override lazy val context: ApplicationLoader.Context =
    ApplicationLoader.createContext(Environment.simple(mode = Mode.Test))

  override def components: MainModule = new MainModule(context)
}
