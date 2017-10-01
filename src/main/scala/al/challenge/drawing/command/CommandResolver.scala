package al.challenge.drawing.command

trait CommandResolver[T] {
  def resolveCommand(commandString: T): Option[DrawingCommand]
}
