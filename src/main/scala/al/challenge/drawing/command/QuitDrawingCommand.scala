package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas

case object QuitDrawingCommand extends DrawingCommand   {
  override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = canvas
}
