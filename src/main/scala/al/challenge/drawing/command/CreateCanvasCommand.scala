package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas

case class CreateCanvasCommand(width: Int, height: Int) extends DrawingCommand {
  override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = Some(new Canvas(width, height))
}
