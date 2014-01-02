package com.howzat.draw

import com.howzat.draw.commands._
import com.howzat.io.{Printer, InputValidation, InputParser}
import com.howzat.model._
import com.howzat.draw.commands.DrawLine
import com.howzat.draw.commands.DrawRectangle
import com.howzat.model.Canvas
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.NewCanvas
import com.howzat.model.FillPoint
import com.howzat.draw.commands.Quit
import com.howzat.draw.commands.DrawLine
import scala.Some
import com.howzat.{ConsoleDrawingApp, CommandEither, DrawingSession}


object ConsoleDrawingMain extends App {

  private val validation = new CommandValidation(new InputValidation)
  private val parser     = new InputParser(new InputValidation)
  private val app        = new ConsoleDrawingApp()

  private def prompt() = print("Enter command:")

  prompt()

  while(true) {
    val line = Console.readLine trim()
    parser parse line map (processInput(_)) getOrElse {
      output(s"Oops: '$line' is not recognised as a valid command")
      prompt()
    }
  }

  def processInput(input: CommandEither) {
    validate(input) match {
      case Left(error) => output(error)
      case Right(command) => command match {
        case Quit() => System.exit(0)
        case c:Command => output(app.enterCommand(c))
      }
    }
  }

  def validate(input: CommandEither): CommandEither = {
    for {
      command <- input.right
      valid   <- validation isValid (command) right
    } yield valid
  }

  def output(cmd: Any) {
    println(cmd)
    prompt()
  }
}


