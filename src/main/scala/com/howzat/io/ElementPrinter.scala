package com.howzat.io

import com.howzat.model._
import scala._
import com.howzat.draw.model.Position
import scala.collection.immutable
import com.howzat.draw.model.Position
import com.howzat.draw.model.Position
import com.howzat.model.Empty
import com.howzat.model.Rectangle
import com.howzat.model.Canvas
import com.howzat.model.Line
import com.howzat.model.FillPoint
import com.howzat.model.HBorder

case class ElementPrinter(emptyChar:String="o") {

  def printCanvas(canvas: Canvas): String = {
    val rowStrings = for {
      element <- canvas.getElements
    } yield {
      if(canvas.isRightEdge(element.bottomRight)) render(element) + "\n"
      else render(element)
    }
    (rowStrings mkString("")) .dropRight(1)
  }

  def render(element: Element): String = {
    element match {
      case HBorder(_) => "|"
      case VBorder(_) => "-"
      case Line(_, _) => "x"
      case Rectangle(_, _) => "x"
      case Empty(_) => emptyChar
      case FillPoint(_, colour) => colour
      case _ => "?"
    }
  }
}
