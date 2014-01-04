package com.howzat.io

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.{Element, Empty, HBorder, Canvas}
import com.howzat.draw.model.Position._

@RunWith(classOf[JUnitRunner])
class ElementPrinterTest extends FreeSpec with ShouldMatchers  {

  val printer = ElementPrinter()
  val emptyRow = List(HBorder((0,1)), Empty((1,1)), Empty((2,1)), HBorder((3,1)))

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
