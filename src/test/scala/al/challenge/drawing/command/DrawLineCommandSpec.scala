package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas
import org.scalatest.{Assertions, WordSpec}

class DrawLineCommandSpec extends WordSpec with Assertions {

  val command = DrawLineCommand(1, 1, 1, 0)
  val command2 = DrawLineCommand(2, 0, 1, 1)
  val canvas = new Canvas(3, 3)

  "The DrawLineCommand" should {

    "draw line" in {
      val newCanvas = command.drawOnCanvas(Some(canvas))
      assert(!newCanvas.forall(_.pixels sameElements canvas.pixels))

      assert(newCanvas.map(_.pixels).forall(pixels => pixels(1)(0) == 'x' && pixels(1)(1) == 'x'))
    }

    "not draw not straight line" in {
      val newCanvas = command2.drawOnCanvas(Some(canvas))
      assert(newCanvas.forall(_.pixels sameElements canvas.pixels))
    }
  }

}
