package com.howzat.io

import java.io.InputStream
import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}
import com.howzat.{CommandEither, draw}
import com.howzat.draw.commands._
import scala.Some
import scala.Some
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.Quit
import com.howzat.draw.commands.DrawLine
import scala.Some
import com.howzat.draw.commands.DrawRectangle
import com.howzat.draw.model.Position

class InputParser(validation:InputValidation) {

  val CanvasRegex    = """C\s+(\d+)\s+(\d+)""".r
  val RectangleRegex = """R\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".r
  val LineRegex      = """L\s+(\d+)\s+(\d+)\s+(\d+)\s+(\d+)""".r
  val FillRegex      = """B\s+(\d+)\s+(\d+)\s+([A-Za-z])""".r
  val QuitRegex      = """Q""".r

  import validation._
  
  def parse(input: String) = {
    input trim match {
      case CanvasRegex(width, height)  => Some(newCanvas(width, height))
      case RectangleRegex(x1,y1,x2,y2) => Some(newRectangle(x1,y1,x2,y2))
      case LineRegex(x1,y1,x2,y2)      => Some(newLine(x1,y1,x2,y2))
      case FillRegex(x1,y1,colour)     => Some(newFill(x1,y1,colour))
      case QuitRegex()                 => Some(Right(Quit()))
      case _ => None
    }
  }

  private def newFill(x1: String, y1: String, colour: String): CommandEither = {
    validateParameters (
        safelyToInt(x1, "x1"),
        safelyToInt(y1, "y1"),
        nonEmptyString(colour, "colour")) {
      params => // Would have to use HLists to retain type information, but this is safe (if not pleasant)
        val position = (params(0).asInstanceOf[Int], params(1).asInstanceOf[Int])
        val colours = params(2).asInstanceOf[String]
        ApplyFill( position, colours )
    }
  }

  private def newLine(x1: String, y1: String, x2: String, y2: String): CommandEither = {
    validateParameters (
        safelyToInt(x1, "x1"),
        safelyToInt(y1, "y1"),
        safelyToInt(x2, "x2"),
        safelyToInt(y2, "y2")) {
      params => DrawLine( (params(0),params(1)), (params(2),params(3)))
    }
  }

  private def newRectangle(x1: String, y1: String, x2: String, y2: String): CommandEither = {
    validateParameters (
        safelyToInt(x1, "x1"),
        safelyToInt(y1, "y1"),
        safelyToInt(x2, "x2"),
        safelyToInt(y2, "y2")) {
      params => DrawRectangle( (params(0),params(1)), (params(2),params(3)))
    }
  }

  private def newCanvas(width: String, height: String): CommandEither= {
    validateParameters(
        safelyToInt(width,  "width"),
        safelyToInt(height, "height")) {
      params => NewCanvas(params(0), params(1))
    }
  }
}