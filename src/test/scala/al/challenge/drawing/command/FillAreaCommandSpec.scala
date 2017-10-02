package al.challenge.drawing.command

import al.challenge.drawing.system.Canvas
import org.scalatest.{Assertions, WordSpec}

class FillAreaCommandSpec extends WordSpec with Assertions {
  val fillAreaCommand = FillAreaCommand(1, 1, 'o')
  val fillAreaCommand2 = FillAreaCommand(0, 0, 'o')
  val drawRectangleCommand = DrawRectangleCommand(0, 0, 2, 2)
  val canvas = new Canvas(3, 3)

  "The FillAreaCommand" should {
    "fill all" in {
      val newCanvas = fillAreaCommand.drawOnCanvas(Some(canvas))
      assert(!newCanvas.forall(_.pixels sameElements canvas.pixels))
      assert(newCanvas.map(_.pixels).forall(pixels =>
        pixels(0)(0) == 'o' && pixels(0)(1) == 'o' && pixels(0)(2) == 'o' &&
        pixels(1)(0) == 'o' && pixels(1)(1) == 'o' && pixels(1)(2) == 'o' &&
        pixels(2)(0) == 'o' && pixels(2)(1) == 'o' && pixels(2)(2) == 'o'
      ))
    }

    "fill area not covered by rectangle" in {
      var newCanvas = drawRectangleCommand.drawOnCanvas(Some(canvas))
      newCanvas = fillAreaCommand.drawOnCanvas(newCanvas)
      assert(!newCanvas.forall(_.pixels sameElements canvas.pixels))
      assert(newCanvas.map(_.pixels).forall(pixels =>
        pixels(0)(0) == 'x' && pixels(0)(1) == 'x' && pixels(0)(2) == 'x' &&
        pixels(1)(0) == 'x' && pixels(1)(1) == 'o' && pixels(1)(2) == 'x' &&
        pixels(2)(0) == 'x' && pixels(2)(1) == 'x' && pixels(2)(2) == 'x'
      ))
    }

    "fill area covered only by rectangle" in {
      var newCanvas = drawRectangleCommand.drawOnCanvas(Some(canvas))
      newCanvas = fillAreaCommand2.drawOnCanvas(newCanvas)
      assert(!newCanvas.forall(_.pixels sameElements canvas.pixels))
      assert(newCanvas.map(_.pixels).forall(pixels =>
        pixels(0)(0) == 'o' && pixels(0)(1) == 'o' && pixels(0)(2) == 'o' &&
        pixels(1)(0) == 'o' && pixels(1)(1) == ' ' && pixels(1)(2) == 'o' &&
        pixels(2)(0) == 'o' && pixels(2)(1) == 'o' && pixels(2)(2) == 'o'
      ))
    }
  }
}
