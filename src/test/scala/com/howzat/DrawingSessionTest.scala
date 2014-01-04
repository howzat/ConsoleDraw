package com.howzat

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model._
import com.howzat.draw.model.Position
import com.howzat.model.VBorder
import com.howzat.model.HBorder
import com.howzat.draw.commands.Command
import com.howzat.io.ElementPrinter

@RunWith(classOf[JUnitRunner])
class DrawingSessionTest extends FreeSpec with ShouldMatchers {


  val empty = Right(Canvas(2, 2,
    List(
      List(VBorder(Position(0,0)), VBorder(Position(1,0)), VBorder(Position(2,0)), VBorder(Position(3,0))),
      List(HBorder(Position(0,1)), Empty(Position(1,1)), Empty(Position(2,1)), HBorder(Position(3,1))),
      List(HBorder(Position(0,2)), Empty(Position(1,2)), Empty(Position(2,2)), HBorder(Position(3,2))),
      List(VBorder(Position(0,3)), VBorder(Position(1,3)), VBorder(Position(2,3)), VBorder(Position(3,3))))
  ))

  "Drawing session without an active canvas" - {

    val session = new DrawingSession()

    "fails attempts to place any elements" in {
      session drawRectangle ((20, 20), (40, 40)) should be(Left("you must create a canvas before using draw commands e.g. 'C 10 10'"))
    }

    "can create new canvas'" in {
      session newCanvas(2, 2) should be( empty )
    }

    "update the canvas" in {
      session drawLine(Position(1,1), (1,2)) should be(Right(Canvas(2, 2,
        List(
          List(VBorder(Position(0,0)), VBorder(Position(1,0)), VBorder(Position(2,0)), VBorder(Position(3,0))),
          List(HBorder(Position(0,1)), Empty(Position(1,1)), Empty(Position(2,1)), HBorder(Position(3,1))),
          List(HBorder(Position(0,2)), Empty(Position(1,2)), Empty(Position(2,2)), HBorder(Position(3,2))),
          List(VBorder(Position(0,3)), VBorder(Position(1,3)), VBorder(Position(2,3)), VBorder(Position(3,3))))
      )))
    }

    val printer = ElementPrinter(" ")

    "prints an empty canvas when one is created" in {
      val output =
        """^----------------------
          ^|                    |
          ^|                    |
          ^|                    |
          ^|                    |
          ^----------------------""".stripMargin('^')

      printer printCanvas( (session newCanvas(20,4)).right get )
    }

    "prints a line on the canvas" in {
      val output =
        """^----------------------
           ^|                    |
           ^|xxxxxx              |
           ^|                    |
           ^|                    |
           ^----------------------""".stripMargin('^')

      printer printCanvas( (session drawLine((1,2),(6,2))).right get )
    }
  }
}

