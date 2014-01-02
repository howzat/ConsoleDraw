package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.Canvas
import com.howzat.draw.model.Position
import scala._
import com.howzat.draw.model.Position
import com.howzat.model.Empty
import com.howzat.model.Rectangle
import com.howzat.model.Canvas
import com.howzat.model.Line
import com.howzat.model.FillPoint
import com.howzat.model.HBorder
import com.howzat.model.VBorder


@RunWith(classOf[JUnitRunner])
class CanvasTest extends FreeSpec with ShouldMatchers {

  private val rectangle: Rectangle = Rectangle(Position(20, 20), Position(20, 20))
  private val line     : Line      = Line(Position(20, 20), Position(20, 20))
  private val fill     : FillPoint = FillPoint(Position(30, 20), "x")

  "Canvas of heigth 100 and width 100" - {

    val canvas = Canvas(100, 100)

    "can report given height and width" in {
      canvas.height should be(100)
      canvas.width should be(100)
    }

    "can add a Rectangle element" in {
      val canvas = Canvas(100, 100) + rectangle
      canvas.elements should be(Vector(rectangle))
    }

    "can add a Line element" in {
      val canvas = Canvas(100, 100) + line
      canvas.elements should be(Vector(line))
    }

    "can add a Fill element" in {
      val canvas = Canvas(100, 100) + fill
      canvas.elements should be(Vector(fill))
    }

    "can add both a Line and Fill elements" in {
      val canvas = Canvas(100, 100) + line + fill
      canvas.elements should be(Vector(fill, line))
    }
  }


  "Can turn a canvas into an element grid with border" - {

    val canvas: Canvas = Canvas(2, 2)

    "find vertical borders" in {
      canvas isTopBorder (Position(0, 0)) should be(true)
      canvas isTopBorder (Position(1, 0)) should be(true)
      canvas isTopBorder (Position(2, 0)) should be(true)
      canvas isTopBorder (Position(3, 0)) should be(true)
      canvas isBottomBorder (Position(0, 3)) should be(true)
      canvas isBottomBorder (Position(1, 3)) should be(true)
      canvas isBottomBorder (Position(2, 3)) should be(true)
      canvas isBottomBorder (Position(3, 3)) should be(true)
      canvas isVerticalBorder (Position(0, 0)) should be(true)
      canvas isVerticalBorder (Position(1, 0)) should be(true)
      canvas isVerticalBorder (Position(2, 0)) should be(true)
      canvas isVerticalBorder (Position(3, 0)) should be(true)
      canvas isVerticalBorder (Position(0, 3)) should be(true)
      canvas isVerticalBorder (Position(1, 3)) should be(true)
      canvas isVerticalBorder (Position(2, 3)) should be(true)
      canvas isVerticalBorder (Position(3, 3)) should be(true)
      canvas isVerticalBorder (Position(1, 1)) should be(false)
    }

    "find horizontal borders" in {
      canvas isLeftBorder (Position(0, 0)) should be(false)
      canvas isLeftBorder (Position(0, 1)) should be(true)
      canvas isLeftBorder (Position(0, 2)) should be(true)
      canvas isLeftBorder (Position(0, 3)) should be(false)
      canvas isRightBorder (Position(3, 0)) should be(false)
      canvas isRightBorder (Position(3, 1)) should be(true)
      canvas isRightBorder (Position(3, 2)) should be(true)
      canvas isRightBorder (Position(3, 3)) should be(false)
      canvas isHorizontalBorder (Position(0, 0)) should be(false)
      canvas isHorizontalBorder (Position(0, 1)) should be(true)
      canvas isHorizontalBorder (Position(0, 2)) should be(true)
      canvas isHorizontalBorder (Position(0, 3)) should be(false)
      canvas isHorizontalBorder (Position(3, 0)) should be(false)
      canvas isHorizontalBorder (Position(3, 1)) should be(true)
      canvas isHorizontalBorder (Position(3, 2)) should be(true)
      canvas isHorizontalBorder (Position(3, 3)) should be(false)
      canvas isHorizontalBorder (Position(1, 1)) should be(false)
    }


    "2 x 2 grid" in {
      canvas.toElementGrid should be(
        List(
              List(VBorder(Position(0, 0)), VBorder(Position(1, 0)), VBorder(Position(2, 0)), VBorder(Position(3, 0))),
              List(HBorder(Position(0, 1)), Empty(Position(1, 1)), Empty(Position(2, 1)), HBorder(Position(3, 1))),
              List(HBorder(Position(0, 2)), Empty(Position(1, 2)), Empty(Position(2, 2)), HBorder(Position(3, 2))),
              List(VBorder(Position(0, 3)), VBorder(Position(1, 3)), VBorder(Position(2, 3)), VBorder(Position(3, 3)))
            )
      )
    }
  }
}
