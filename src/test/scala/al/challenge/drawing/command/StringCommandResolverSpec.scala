package al.challenge.drawing.command

import org.scalatest.{Assertions, WordSpec}

class StringCommandResolverSpec extends WordSpec with Assertions {
  private val commandResolver = new StringCommandResolver

  "The CommandResolver" should {
    import commandResolver.resolveCommand

    "resolve CreateCanvasCommand" in {
      val resolvedCommand = resolveCommand("C 3 4")

      assert(resolvedCommand === Some(CreateCanvasCommand(3, 4)))
    }

    "resolve DrawLineCommand" in {
      val resolvedCommand = resolveCommand("L 1 2 6 2")

      assert(resolvedCommand === Some(DrawLineCommand(0, 1, 5, 1)))
    }

    "resolve DrawRectangleCommand" in {
      val resolvedCommand = resolveCommand("R 16 1 20 3")

      assert(resolvedCommand === Some(DrawRectangleCommand(15, 0, 19, 2)))
    }

    "resolve FillAreaCommand" in {
      val resolvedCommand = resolveCommand("B 10 3 o")

      assert(resolvedCommand === Some(FillAreaCommand(9, 2, 'o')))
    }

    "resolve FillAreaCommand with special char" in {
      val resolvedCommand = resolveCommand("B 10 3 .")

      assert(resolvedCommand === Some(FillAreaCommand(9, 2, '.')))
    }

    "resolve FillAreaCommand with another special char" in {
      val resolvedCommand = resolveCommand("B 10 3 ~")

      assert(resolvedCommand === Some(FillAreaCommand(9, 2, '~')))
    }

    "resolve QuitDrawingCommand" in {
      val resolvedCommand = resolveCommand("Q")

      assert(resolvedCommand === Some(QuitDrawingCommand))
    }

    "fail to resolve invalid command" in {
      assert(resolveCommand("C 0 1") === None)
      assert(resolveCommand("C 1 0") === None)
      assert(resolveCommand("C 0 0") === None)
      assert(resolveCommand("aa cc vv") === None)
      assert(resolveCommand("Q 1") === None)
      assert(resolveCommand("B 10 3 ff") === None)
      assert(resolveCommand("R 1 1 1 f") === None)
    }
  }
}
