package al.challenge.drawing.system

import org.scalatest.{Assertions, WordSpec}

import scala.language.implicitConversions

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


      def newLine = System.lineSeparator()

      val expectedCanvas = newLine + allRows.mkString(newLine) + newLine
      assert(expectedCanvas === canvasString, s"built canvas $canvasString must be equal to expectedCanvas $expectedCanvas")
    }

  }

}
