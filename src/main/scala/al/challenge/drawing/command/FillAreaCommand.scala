package al.challenge.drawing.command

case class FillAreaCommand(x: Int, y: Int, colour: Char) extends BaseDrawingCommand {
  override protected val customCommandFilter: Pixels => Boolean = pixels =>
    isCoordinateWithinCanvas(pixels, x, y)

  override protected val doDraw: Pixels => Unit = pixels => {
    def findNeighbourPixels(x: Int, y: Int)(implicit includeForPainting: Char => Boolean) =
      Seq(x -> y, (x - 1) -> y, (x + 1) -> y, x -> (y - 1), x -> (y + 1))
        .filter(coordinate => isCoordinateWithinCanvas(pixels, coordinate))
        .filter({ case (_x, _y) => includeForPainting(pixels(_x)(_y)) })

    def fill(neighbourPixels: Seq[(Int, Int)])(implicit includeForPainting: Char => Boolean): Unit = {
      neighbourPixels.foreach({ case (_x, _y) =>
        pixels(_x)(_y) = colour
        fill(findNeighbourPixels(_x, _y))
      })
    }

    val pixelToSearch = pixels(x)(y)
    implicit val includeForPainting: Char => Boolean = pixel => pixel == pixelToSearch

    fill(findNeighbourPixels(x, y))
  }
}
