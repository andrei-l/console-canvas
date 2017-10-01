package al.challenge.drawing.command

case class DrawRectangleCommand(x1: Int, y1: Int, x2: Int, y2: Int) extends BaseDrawingCommand {
  override protected val customCommandFilter: Pixels => Boolean = pixels =>
    isCoordinateWithingCanvas(pixels, x1, y1) && isCoordinateWithingCanvas(pixels, x2, y2)

  override protected val doDraw: Pixels => Unit = pixels => {
    for {
      i <- math.min(x1, x2) to math.max(x1, x2)
    } {
      pixels(i)(y1) = 'x'
      pixels(i)(y2) = 'x'
    }

    for {
      j <- math.min(y1, y2) to math.max(y1, y2)
    } {
      pixels(x1)(j) = 'x'
      pixels(x2)(j) = 'x'
    }
  }
}
