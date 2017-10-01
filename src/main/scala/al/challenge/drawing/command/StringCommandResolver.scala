package al.challenge.drawing.command

import scala.Predef.augmentString
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
        case CreateCanvasCommandPattern(width, height) => CreateCanvasCommand(width.toInt, height.toInt)
        case DrawLineCommandPattern(x1, y1, x2, y2) => DrawLineCommand(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
        case DrawRectangleCommandPattern(x1, y1, x2, y2) => DrawRectangleCommand(x1.toInt, y1.toInt, x2.toInt, y2.toInt)
        case FillAreaCommandPattern(x, y, colour) if colour.length == 1 => FillAreaCommand(x.toInt, y.toInt, colour(0))
        case QuitDrawingCommandPattern() => QuitDrawingCommand
      }
    }.toOption
  }
}
