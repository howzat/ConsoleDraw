package com.howzat

import com.howzat.io.InputParser
import com.howzat.commands._
import com.howzat.io.ElementPrinter


object ConsoleDrawingMain extends App {

  private val validation = new CommandValidation()
  private val parser     = new InputParser()
  private var session    = new DrawingSession()
  private var printer    = ElementPrinter(" ")

  private def prompt() = print("Enter command:")

  prompt()

  while(true) {
    read(Console.readLine trim())
  }

  private def output(cmd: Any) {
    println(cmd)
    prompt()
  }

  def read(line: String) {
    parser parse line map (processInput(_)) getOrElse {
      output(s"Oops: '$line' is not recognised as a valid command")
    }
  }

  private def processInput(input: CommandEither) {
    validate(input) match {
      case Left(error) => output(error)
      case Right(command) => command match {
        case Quit() => System.exit(0)
        case c: Command => output(renderCanvas(c))
      }
    }
  }

  private def validate(input: CommandEither): CommandEither = {
    for {
      command <- input.right
      valid <- validation isValid (command) right
    } yield valid
  }

  private def renderCanvas(command: Command): String = {
    draw(command) match {
      case Right(canvas) => printer printCanvas canvas
      case Left(errorString) => s"Oops: $errorString"
    }
  }

  private def draw(command: Command): CanvasEither = {
    command match {
      case NewCanvas(width, height) => session newCanvas(width, height)
      case ApplyFill(position, colour) => session fill(position, colour)
      case DrawLine(topLeft, topRight) => session drawLine(topLeft, topRight)
      case DrawRectangle(topLeft, topRight) => session drawRectangle(topLeft, topRight)      
    }
  }
}


