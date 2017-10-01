package al.challenge.drawing.io

import al.challenge.drawing.command.DrawingCommand

trait CommandInput {
  def readDrawingCommand: Option[DrawingCommand]
}
