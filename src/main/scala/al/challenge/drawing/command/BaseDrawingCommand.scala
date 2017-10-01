package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas

private[command] trait BaseDrawingCommand extends DrawingCommand {
  type Pixels = Array[Array[Char]]

  override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = {
    canvas
      .map(_.pixels)
      .filter(customCommandFilter)
      .map(copy)
      .map {
        pixels =>
          doDraw(pixels)
          pixels
      }
      .map(Canvas.apply)
      .orElse(canvas)
  }

  protected val customCommandFilter: Pixels => Boolean
  protected val doDraw: Pixels => Unit

  private[command] def isCoordinateWithinCanvas(pixels: Pixels, x: Int, y: Int): Boolean = {
    x >= 0 && x < pixels.length && y >= 0 && y < pixels(0).length
  }

  private[command] def copy(pixels: Pixels): Pixels =
    pixels.map(_.clone())
}
