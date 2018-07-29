package controllers

import play.api.Environment
import play.api.mvc._

import scala.concurrent.ExecutionContext

class HomeController()(
  implicit
  val environment: Environment,
  val executionContext: ExecutionContext,
  val controllerComponents: ControllerComponents) extends BaseController {

  def index = Action {
    Ok("hello!")
  }

}
