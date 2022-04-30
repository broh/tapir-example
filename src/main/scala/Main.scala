import sttp.tapir.EndpointIO.annotations.query
import sttp.tapir.Schema.annotations.validate
import sttp.tapir.generic.auto._
import sttp.tapir.json.zio._
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import sttp.tapir.ztapir._
import sttp.tapir.{EndpointInput, Validator}
import zhttp.service.Server
import zio._

object Main extends App {
  case class TapirRequest(
    @query
//    @validate(Validator.minLength(5))
    foo: Option[String],

    @query
    @validate(Validator.minLength[String](5))
    bar: String
  )

  var tapirEndpoint = endpoint
    .get
    .in(EndpointInput.derived[TapirRequest])
    .out(plainBody[String])
    .zServerLogic[ZEnv] { case (request) =>
      ZIO.succeed(s"Params: foo=${request.foo.getOrElse("NONE")}; bar=${request.bar}")
    }

  val app = ZioHttpInterpreter().toHttp(tapirEndpoint)

  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    Server.start(8090, app).exitCode
}
