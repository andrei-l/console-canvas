package al.challenge.drawing.io.console

import al.challenge.drawing.io.CanvasOutput
import al.challenge.drawing.system.Canvas

class ConsoleCanvasOutput extends CanvasOutput {
  override def writeCanvas(canvas: Option[Canvas]): Unit =
    canvas.foreach(println)
}
