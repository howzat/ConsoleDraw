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


  val empty = List(
      VBorder(Position(0, 0)), VBorder(Position(1, 0)), VBorder(Position(2, 0)), VBorder(Position(3, 0)),
      HBorder(Position(0, 1)), Empty(Position(1, 1)), Empty(Position(2, 1)), HBorder(Position(3, 1)),
      HBorder(Position(0, 2)), Empty(Position(1, 2)), Empty(Position(2, 2)), HBorder(Position(3, 2)),
      VBorder(Position(0, 3)), VBorder(Position(1, 3)), VBorder(Position(2, 3)), VBorder(Position(3, 3)))


  "Drawing session " - {

    val session = new DrawingSession()

    "fails attempts to place any elements" in {
      session drawRectangle((20, 20), (40, 40)) should be(Left("you must create a canvas before using draw commands e.g. 'C 10 10'"))
    }

    "can create new canvas'" in {
      val canvas: Canvas = Canvas(2, 2, Nil)
      session newCanvas(2, 2) should be(Right(canvas))
      canvas.getElements should be(empty)

    }

    "updates the canvas " in {
      session drawLine(Position(1, 1), (1, 2)) should be(Right(Canvas(2, 2,
         List(VBorder(Position(0, 0)), VBorder(Position(1, 0)), VBorder(Position(2, 0)), VBorder(Position(3, 0)),
         HBorder(Position(0, 1)), Line(Position(1, 1),Position(1, 1)), Empty(Position(2, 1)), HBorder(Position(3, 1)),
         HBorder(Position(0, 2)), Line(Position(1, 2),Position(1, 2)), Empty(Position(2, 2)), HBorder(Position(3, 2)),
         VBorder(Position(0, 3)), VBorder(Position(1, 3)), VBorder(Position(2, 3)), VBorder(Position(3, 3))))))
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

      val opt = session newCanvas(20, 4)
      opt.isRight should be(true)
      val canvas = printer printCanvas (opt.right.get)
      canvas should be(output)
    }

    "prints a line on the canvas" in {
      val output =
        """^----------------------
          ^|                    |
          ^|xxxxxx              |
          ^|                    |
          ^|                    |
          ^----------------------""".stripMargin('^')

      val opt = session drawLine((1, 2), (6, 2))
      opt.isRight should be(true)
      val canvas = printer printCanvas (opt.right.get)
      canvas should be(output)
    }


    "prints a second line on the canvas" in {
      val output =
        """^----------------------
          ^|                    |
          ^|xxxxxx              |
          ^|     x              |
          ^|     x              |
          ^----------------------""".stripMargin('^')

      val opt = session drawLine((6, 3), (6, 4))
      opt.isRight should be(true)
      val canvas = printer printCanvas (opt.right.get)
      println(canvas)
      canvas should be(output)
    }

    "prints a rectangle line on the canvas" in {
      val output =
        """^----------------------
          ^|               xxxxx|
          ^|xxxxxx         x   x|
          ^|     x         xxxxx|
          ^|     x              |
          ^----------------------""".stripMargin('^')

      val opt = session drawRectangle((16, 1), (20, 3))
      opt.isRight should be(true)
      val canvas = printer printCanvas (opt.right.get)
      canvas should be(output)
    }


    "fills a colour into the canvas" in {
      val output =
            """^----------------------
              ^|oooooooooooooooxxxxx|
              ^|xxxxxxooooooooox   x|
              ^|     xoooooooooxxxxx|
              ^|     xoooooooooooooo|
              ^----------------------""".stripMargin('^')

      val opt = session fill((10,3),"o")
      opt.isRight should be(true)
      val canvas = printer printCanvas (opt.right.get)
      canvas should be(output)
    }
  }
}