package com.howzat

import com.howzat.draw.commands._
import com.howzat.io.{Printer, InputParser, InputValidation}
import com.howzat.model._
import scala.Some
import scala.Some
import scala.Some
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.Quit
import scala.Some
import com.howzat.draw.commands.NewCanvas
import com.howzat.draw.commands.DrawLine
import com.howzat.model.Line
import com.howzat.model.FillPoint
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.Quit
import scala.Some
import com.howzat.draw.commands.NewCanvas
import com.howzat.draw.commands.DrawLine
import com.howzat.draw.commands.DrawRectangle
import java.io.{OutputStream, InputStream}
import scala.io.{BufferedSource, Source}

class ConsoleDrawingApp() {

  private val validation     = new CommandValidation(new InputValidation)
  private val parser         = new InputParser(new InputValidation)
  private var drawingSession = new DrawingSession(Layout.default)

  def enterCommand(command: Command): String = {
    execute(command) match {
      case Right(canvas) => Printer printCanvas canvas
      case Left(errorString) => errorString
    }
  }

  def execute(command: Command): CanvasEither = {
    command match {
      case NewCanvas(w, h) => Right((drawingSession newCanvas(w, h)))
      case _ => toElement(command) map (drawingSession placeElement (_)) map {
        _ match {
          case Right(updated) => Right(updated)
          case Left(errors) => Left(s"Oops: $errors")
        }
      } getOrElse Left(s"$command is and unrecognised command")
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
}
