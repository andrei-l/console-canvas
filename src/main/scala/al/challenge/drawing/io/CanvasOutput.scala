package al.challenge.drawing.io

import al.challenge.drawing.system.Canvas

trait CanvasOutput {
  def writeCanvas(canvas: Option[Canvas]): Unit
}
