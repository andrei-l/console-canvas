package al.challenge.drawing.system

import org.scalatest.{Assertions, WordSpec}

class CanvasSpec extends WordSpec with Assertions {

  "The Canvas" should {

    "convert to string properly" in {
      val width = 20
      val height = 4
      val canvasString = new Canvas(width, height).toString

      val startRow, endRow = (0 to width + 1).map(_ => '-').mkString
      val row = '|' + (0 until width).map(_ => ' ').mkString + '|'
      val rows = (for (_ <- 0 until height) yield row).toList
      val allRows: List[String] = startRow :: rows ::: endRow :: Nil


      assert(allRows.mkString(System.lineSeparator()) === canvasString)
    }

  }
}
