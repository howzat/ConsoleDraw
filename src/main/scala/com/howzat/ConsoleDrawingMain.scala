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
import com.howzat.{Result, DrawingSession}


object ConsoleDrawingMain extends App {

  private val validation     = new CommandValidation(new InputValidation)
  private val parser         = new InputParser(new InputValidation)
  private var drawingSession = new DrawingSession(Layout.default)
  private def prompt() = print("Enter command:")

  prompt()

  while(true) {
    val line = Console.readLine trim()
    parser parse line map (runInputCommand(_)) getOrElse {
      output(s"'$line' is not recognised as a valid command")
      prompt()
    }
  }

  private def runInputCommand(input: Result) {
    validate(input) match {
      case Left(error) => output(error)
      case Right(cmnd) => output(execute(cmnd))
    }
  }

  private def execute(command: Command) = {
    command match {
      case Quit() => System.exit(0)
      case NewCanvas(w, h) => drawingSession newCanvas(w, h)
      case _ => toElement(command) map (drawingSession placeElement (_)) map {
        _ match {
          case Right(updated) => Printer print updated
          case Left(errors) => s"Oops: $errors"
        }
      } get
    }
  }

  private def toElement(command: Command): Option[Element] = {
    command match {
      case DrawLine(tl, br) => Some(Line(tl, br))
      case ApplyFill(pos, c) => Some(FillPoint(pos, c))
      case DrawRectangle(tl, br) => Some(Rectangle(tl, br))
      case _ => None
    }
  }

  def validate(input: Result): Result = {
    for {
      command <- input.right
      valid <- validation isValid (command) right
    } yield valid
  }

  def output(cmd: Any) {
    println(cmd)
    prompt()
  }
}


