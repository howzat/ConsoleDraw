package com.howzat.draw

import com.howzat.draw.commands._
import com.howzat.io.{InputValidation, InputParser}
import com.howzat.model._
import com.howzat.draw.commands.DrawLine
import com.howzat.draw.commands.DrawRectangle
import com.howzat.model.Canvas
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.NewCanvas
import com.howzat.model.Fill
import com.howzat.draw.commands.Quit
import com.howzat.draw.commands.DrawLine
import scala.Some


object CanvasMain extends App {

  import BasicLayout._

  private val validation             = new CommandValidation(new InputValidation)
  private val parser                 = new InputParser(new InputValidation)
  private var canvas: Option[Canvas] = None

  private def prompt() = print("Enter command:")

  prompt()

  while(true) {
    val line = Console.readLine trim()
    parser parse line map {
      input =>
        getCommand(input) match {
          case Left(error) => output(error)
          case Right(command) => command match {
            case Quit() => System.exit(0)
            case NewCanvas(w, h) => canvas = Some(Canvas(w, h))
            case _ => canvas map (updateCanvas(command, _)) getOrElse {
              output("you must create a canvas before using draw commands e.g. 'C 10 10'")
              prompt()
            }
          }
        }
    } getOrElse unknownCommand(line)
  }

  def updateCanvas(command: Command, c: Canvas): Either[String, Canvas] = {
    toElement(command) map {
      element => placeElement(element, c)
    } getOrElse Right(c)
  }


  def toElement(command: Command): Option[Element] = {
    command match {
      case cmd@DrawLine(tl, br) => Some(Line(tl, br))
      case cmd@ApplyFill(pos, c) => Some(Fill(pos, c))
      case cmd@DrawRectangle(tl, br) => Some(Rectangle(tl, br))
      case _ => None
    }
  }

  def getCommand(parsedInput: Either[String, Command]): Either[String, Command] = {
    for {
      command <- parsedInput.right
      valid <- validation.isValid(command).right
    } yield valid
  }

  def output(cmd: Any) {
    println(s"$cmd")
    prompt()
  }

  def unknownCommand(line: String) {
    println(s"'$line' is not recognised as a valid command")
    prompt()
  }
}