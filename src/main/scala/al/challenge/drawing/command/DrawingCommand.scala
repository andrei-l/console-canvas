package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas

trait DrawingCommand {
  def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas]
}
