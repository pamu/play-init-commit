package play.api.libs.circe

import io.circe._
import play.api.http._
import play.api.mvc._

trait CirceJsonWritableImplicits {

  val defaultPrinter: Printer = Printer.noSpaces

  implicit val contentTypeOf_Json: ContentTypeOf[Json] = {
    ContentTypeOf(Some(ContentTypes.JSON))
  }

  implicit def writableOf_Json(implicit codec: Codec, printer: Printer = defaultPrinter): Writeable[Json] = {
    Writeable(a => codec.encode(a.pretty(printer)))
  }
}

object CirceJsonWritable extends CirceJsonWritableImplicits
