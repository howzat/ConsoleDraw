package com.howzat.io

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.{Element, Empty, HBorder, Canvas}
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class BorderedPrinterTest extends FreeSpec with ShouldMatchers  {

  val printer = BorderedPrinter()

  "Canvas Printer " - {

    "renders an empty row " in {
      printer renderRow (makeEmptyRow(2)) should be ("|oo|")
    }

    "renders multiple empty rows " in {
      val row: List[Element] = makeEmptyRow(2)
      printer renderRows(List(row, row), "") should be ("|oo|\n|oo|")
    }

    "prints empty canvas" in {

      val output =
        """------
          ^|oooo|
          ^|oooo|
          ^|oooo|
          ^|oooo|
          ^------""".stripMargin('^')

      printer printCanvas (Canvas(4,4)) should be (output)
    }
  }

  def makeEmptyRow(size:Int): List[Element] = {
    List.tabulate(size)(xPos => Empty(Position(xPos + 1, 1)))
  }
}
