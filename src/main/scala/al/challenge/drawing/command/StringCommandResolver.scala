package al.challenge.drawing.command

import java.lang.Integer.parseInt

import scala.Predef.augmentString
import scala.language.implicitConversions
import scala.util.Try

class StringCommandResolver extends CommandResolver[String] {
  private final val CreateCanvasCommandPattern = """C (\d+) (\d+)""".r
  private final val DrawLineCommandPattern = """L (\d+) (\d+) (\d+) (\d+)""".r
  private final val DrawRectangleCommandPattern = """R (\d+) (\d+) (\d+) (\d+)""".r
  private final val FillAreaCommandPattern = """B (\d+) (\d+) (\w)""".r
  private final val QuitDrawingCommandPattern = """Q""".r

  override def resolveCommand(commandString: String): Option[DrawingCommand] = {
    Try {
      commandString match {
        case CreateCanvasCommandPattern(width, height) if parseInt(width) > 0 && parseInt(height) > 0 =>
          CreateCanvasCommand(parseInt(width), parseInt(height))
        case DrawLineCommandPattern(x1, y1, x2, y2) => DrawLineCommand(x1, y1, x2, y2)
        case DrawRectangleCommandPattern(x1, y1, x2, y2) => DrawRectangleCommand(x1, y1, x2, y2)
        case FillAreaCommandPattern(x, y, colour) if colour.length == 1 => FillAreaCommand(x, y, colour(0))
        case QuitDrawingCommandPattern() => QuitDrawingCommand
      }
    }.toOption
  }

  private implicit def coordinateToArrayIndex(coordinate: String): Int = parseInt(coordinate) - 1
}
