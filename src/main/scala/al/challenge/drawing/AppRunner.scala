package al.challenge.drawing

import al.challenge.drawing.command.{QuitDrawingCommand, StringCommandResolver}
import al.challenge.drawing.io.console.{ConsoleCanvasOutput, ConsoleCommandInput}
import al.challenge.drawing.io.{CanvasOutput, CommandInput}
import al.challenge.drawing.system.Canvas

object AppRunner extends App {
  private val commandInput: CommandInput = new ConsoleCommandInput(new StringCommandResolver)
  private val canvasOutput: CanvasOutput = new ConsoleCanvasOutput

  private var canvas: Option[Canvas] = None

  private def proceedWithDrawing(): Unit =
    commandInput.readDrawingCommand match {
      case Some(QuitDrawingCommand) =>
      case Some(otherDrawingCommand) =>
        canvas = otherDrawingCommand.drawOnCanvas(canvas)
        canvasOutput.writeCanvas(canvas)
        proceedWithDrawing()
      case None =>
        println("Invalid command")
        proceedWithDrawing()
    }

  proceedWithDrawing()
}
