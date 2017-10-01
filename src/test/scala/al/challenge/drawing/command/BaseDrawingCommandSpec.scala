package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas
import org.scalatest.{Assertions, WordSpec}

class BaseDrawingCommandSpec extends WordSpec with Assertions {

  object SampleCommand extends BaseDrawingCommand {
    override def drawOnCanvas(canvas: Option[Canvas]): Option[Canvas] = canvas

    override protected val customCommandFilter: SampleCommand.Pixels => Boolean = _ => true

    override protected val doDraw: SampleCommand.Pixels => Unit = _ => ()
  }

  "The BaseDrawingCommand" should {
    "check whether coordinate is within canvas" in {
      val pixels = Array.ofDim[Char](20, 4)
      assert(SampleCommand.isCoordinateWithinCanvas(pixels, 0, 1))
      assert(SampleCommand.isCoordinateWithinCanvas(pixels, 5, 1))
      assert(!SampleCommand.isCoordinateWithinCanvas(pixels, -1, 1))
      assert(!SampleCommand.isCoordinateWithinCanvas(pixels, 4, 4))
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
