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
  val emptyRow = List(HBorder(Position(0,1)), Empty(Position(1,1)), Empty(Position(2,1)), HBorder(Position(3,1)))

  "Canvas Printer " - {

    "renders an empty row " in {
      printer renderRow (emptyRow) should be ("|oo|")
    }

    "renders multiple empty rows " in {
      val row: List[Element] = emptyRow
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


}
