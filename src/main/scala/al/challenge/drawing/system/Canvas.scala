package al.challenge.drawing.system

class Canvas(val pixels: Array[Array[Char]]) {

  def this(width: Int, height: Int) =
    this(Array.fill[Char](width, height)(' '))

  override def toString: String = {
    pixels.length match {
      case 0 => Canvas.toString(pixels, 0, 0)
      case other => Canvas.toString(pixels, other, pixels(0).length)
    }
  }
}

object Canvas {
  def toString(pixels: Array[Array[Char]], width: Int, height: Int): String = {
    val sb = StringBuilder.newBuilder

    def newLine = System.lineSeparator()

    val startRow, endRow = (0 to width + 1).map(_ => '-').mkString

    for {
      j <- 0 until height
      i <- 0 until width
    } yield {
      if (i == 0) sb.append('|')
      sb.append(pixels(i)(j))
      if (i + 1 == width) sb.append('|')
      if (i + 1 == width) sb.append(newLine)
    }

    StringBuilder.newBuilder
      .append(startRow).append(newLine).append(sb).append(endRow)
      .toString()
  }
}
