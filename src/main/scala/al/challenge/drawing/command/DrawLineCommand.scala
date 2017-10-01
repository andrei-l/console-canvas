package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas

case class DrawLineCommand(x1: Int, y1: Int, x2: Int, y2: Int) extends BaseDrawingCommand {

  override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = {
    canvas
      .map(_.pixels)
      .filter(pixels => isCoordinateWithingCanvas(pixels, x1, y1) && isCoordinateWithingCanvas(pixels, x2, y2))
      .filter(_ => isStraightLine)
      .map(copy)
      .map {
        pixels => {
          for {
            j <- math.min(y1, y2) to math.max(y1, y2)
            i <- math.min(x1, x2) to math.max(x1, x2)
          } pixels(i)(j) = 'x'
          pixels
        }
      }
      .map(Canvas.apply)
      .orElse(canvas)
  }

  private def isStraightLine: Boolean =
    x1 == x2 || y1 == y2
}
