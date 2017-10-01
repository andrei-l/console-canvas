package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas
import org.scalatest.{Assertions, WordSpec}

class BaseDrawingCommandSpec extends WordSpec with Assertions {

  object SampleCommand extends BaseDrawingCommand {
    override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = canvas
  }

  "The BaseDrawingCommand" should {
    "check whether coordinate is within canvas" in {
      val pixels = Array.ofDim[Char](20, 4)
      assert(SampleCommand.isCoordinateWithingCanvas(pixels, 0, 1))
      assert(SampleCommand.isCoordinateWithingCanvas(pixels, 5, 1))
      assert(!SampleCommand.isCoordinateWithingCanvas(pixels, -1, 1))
      assert(!SampleCommand.isCoordinateWithingCanvas(pixels, 4, 4))
    }

    "deep copy 2d array" in {
      val pixels = Array.ofDim[Char](20, 4)
      pixels(0)(0) = '4'
      val pixelsCopy = SampleCommand.copy(pixels)
      pixelsCopy(0)(0) = '3'

      assert(pixels(0)(0) === '4')
      assert(pixelsCopy(0)(0) === '3')
    }
  }

}
