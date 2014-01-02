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

case class BorderedPrinter(emptyChar:String="o") {

  private val border = 2

  def addBorder(i: Int, i1: Int, elements: List[List[Element]]) = elements

  def printCanvas(canvas: Canvas): String = {
    renderRows(canvas toElementGrid)
  }

  def renderRows(rows: List[List[Element]], rendered:String="") :String = {
    val rowStrings = for (row <- rows) yield renderRow(row)
    rowStrings mkString("\n")
  }

  def renderRow(elems: List[Element], rendered:String="") : String = elems match {
    case Nil => rendered
    case head :: tail =>
      head match {
        case HBorder(_) => renderRow(tail, rendered + "|")
        case VBorder(_) => renderRow(tail, rendered + "-")
        case Line(_, _) => renderRow(tail, rendered + "*")
        case Rectangle(_, _) => renderRow(tail, rendered + "*")
        case Empty(_) => renderRow(tail, rendered + emptyChar)
        case FillPoint(_, colour) => renderRow(tail, rendered + colour)
        case _ => renderRow(tail, rendered + "?")
    }
  }

  def renderVerticalBorder(i: Int) = {
    List.fill(i)("-") mkString("")
  }
}
