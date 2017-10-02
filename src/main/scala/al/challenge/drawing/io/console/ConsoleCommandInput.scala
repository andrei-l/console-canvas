package al.challenge.drawing.io.console

import al.challenge.drawing.command.{StringCommandResolver, DrawingCommand}
import al.challenge.drawing.io.CommandInput

import scala.io.StdIn

class ConsoleCommandInput(commandResolver: StringCommandResolver) extends CommandInput {

  override def readDrawingCommand: Option[DrawingCommand] = {
    print("enter command: ")
    val commandString = StdIn.readLine()
    commandResolver.resolveCommand(commandString)
  }
}
