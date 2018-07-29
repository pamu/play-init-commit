package loader

import module.MainModule
import play.api.{Application, ApplicationLoader}

class MainLoader extends ApplicationLoader {
  def load(context: ApplicationLoader.Context): Application =
    new MainModule(context).application
}
