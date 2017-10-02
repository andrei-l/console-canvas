package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas
import org.scalatest.{Assertions, WordSpec}

class DrawRectangleCommandSpec extends WordSpec with Assertions {
  val command = DrawRectangleCommand(0, 0, 2, 2)
  val canvas = new Canvas(3, 3)

  "The DrawRectangleCommand" should {

    "draw rectangle" in {
      val newCanvas = command.drawOnCanvas(Some(canvas))
      assert(!newCanvas.forall(_.pixels sameElements canvas.pixels))

      assert(newCanvas.map(_.pixels).forall(pixels =>
        pixels(0)(0) == 'x' && pixels(0)(1) == 'x' && pixels(0)(2) == 'x' &&
        pixels(1)(0) == 'x' && pixels(1)(1) == ' ' && pixels(1)(2) == 'x' &&
        pixels(2)(0) == 'x' && pixels(2)(1) == 'x' && pixels(2)(2) == 'x'
      ))
    }
  }


}
