package com.howzat.model

import org.scalatest.FreeSpec
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import scala._

@RunWith(classOf[JUnitRunner])
class CanvasTest extends FreeSpec with ShouldMatchers {

  private val rectangle: Rectangle = Rectangle((20, 20), (20, 20))
  private val line     : Line      = Line((20, 20), (20, 20))
  private val fill     : FillPoint = FillPoint((30, 20), "x")

  "Canvas of heigth 100 and width 100" - {

    val canvas = Canvas(20, 4, Nil)

    "can report given height and width" in {
      canvas.height should be(4)
      canvas.width should be(20)
    }
  }


  "2 x 2 grid " - {

    val canvas: Canvas = Canvas(2, 2, Nil)

    "element grid with border" in {
      val elements = List(
                           VBorder((0, 0)), VBorder((1, 0)), VBorder((2, 0)), VBorder((3, 0)),
                           HBorder((0, 1)), Empty((1, 1)), Empty((2, 1)), HBorder((3, 1)),
                           HBorder((0, 2)), Empty((1, 2)), Empty((2, 2)), HBorder((3, 2)),
                           VBorder((0, 3)), VBorder((1, 3)), VBorder((2, 3)), VBorder((3, 3))
                         )


      canvas.getElements should be(elements)
    }

    "find vertical edges" in {
      canvas isTopBorder ((0, 0)) should be(true)
      canvas isTopBorder ((1, 0)) should be(true)
      canvas isTopBorder ((2, 0)) should be(true)
      canvas isTopBorder ((3, 0)) should be(true)
      canvas isBottomBorder ((0, 3)) should be(true)
      canvas isBottomBorder ((1, 3)) should be(true)
      canvas isBottomBorder ((2, 3)) should be(true)
      canvas isBottomBorder ((3, 3)) should be(true)
      canvas isVerticalBorder ((0, 0)) should be(true)
      canvas isVerticalBorder ((1, 0)) should be(true)
      canvas isVerticalBorder ((2, 0)) should be(true)
      canvas isVerticalBorder ((3, 0)) should be(true)
      canvas isVerticalBorder ((0, 3)) should be(true)
      canvas isVerticalBorder ((1, 3)) should be(true)
      canvas isVerticalBorder ((2, 3)) should be(true)
      canvas isVerticalBorder ((3, 3)) should be(true)
      canvas isVerticalBorder ((1, 1)) should be(false)
    }

    "find horizontal edges" in {
      canvas isLeftBorder ((0, 0)) should be(false)
      canvas isLeftBorder ((0, 1)) should be(true)
      canvas isLeftBorder ((0, 2)) should be(true)
      canvas isLeftBorder ((0, 3)) should be(false)
      canvas isRightBorder ((3, 0)) should be(false)
      canvas isRightBorder ((3, 1)) should be(true)
      canvas isRightBorder ((3, 2)) should be(true)
      canvas isRightBorder ((3, 3)) should be(false)
      canvas isHorizontalBorder ((0, 0)) should be(false)
      canvas isHorizontalBorder ((0, 1)) should be(true)
      canvas isHorizontalBorder ((0, 2)) should be(true)
      canvas isHorizontalBorder ((0, 3)) should be(false)
      canvas isHorizontalBorder ((3, 0)) should be(false)
      canvas isHorizontalBorder ((3, 1)) should be(true)
      canvas isHorizontalBorder ((3, 2)) should be(true)
      canvas isHorizontalBorder ((3, 3)) should be(false)
      canvas isHorizontalBorder ((1, 1)) should be(false)

      val line1: Line = Line(Position(1, 1), Position(1, 2))
      val line2: Line = Line(Position(1, 1), Position(1, 2))
      line1.topLeft == line2.topLeft should be (true)
      line1.bottomRight == line2.bottomRight should be (true)
    }

    "finds the right edge" in {
      canvas isRightEdge Line((0, 0), (3, 0)) should be(true)
      canvas isRightEdge Line((3, 0), (3, 3)) should be(true)
      canvas isRightEdge ((3, 1)) should be(true)
      canvas isRightEdge ((3, 2)) should be(true)
      canvas isRightEdge ((3, 3)) should be(true)
    }
  }

  "Canvas of 10 x 10" - {

    val canvas = Canvas(10, 10, Nil)

    "should fail to place a position on the border" in {
      canvas withinCanvas ((0, 0)) should be(false)
      canvas withinCanvas ((1, 0)) should be(false)
      canvas withinCanvas ((1, 1)) should be(true)
    }

    "should fail to place elements that wont fit horizontally" in {
      val tooWide = Line((1, 1), (11, 1))
      canvas withinCanvas tooWide should be(false)
    }

    "should fail to place elements that wont fit vertically" in {
      val toTall = Line((1, 1), (1, 11))
      canvas withinCanvas toTall should be(false)

      val elements = Line renderLines(toTall.from, toTall.to)
      canvas withinCanvas elements should be(Left("element Line(Position(1,11),Position(1,11)) is positioned outside of the canvas"))
    }

    "places single points at 1,1" in {
      val elements2 = Line renderLines((1, 1), (1, 1))
      canvas withinCanvas elements2 should be(Right(canvas))
    }

    "fails if the element is outside the canvas" in {
      val line = Line((20, 20), (30, 30))
      canvas withinCanvas line should be(false)

      val elements = Line renderLines((20, 20), (30, 30))
      canvas withinCanvas elements should be(Left("element Line(Position(20,20),Position(20,20)) is positioned outside of the canvas"))
    }

    "fails to place if the element is outside the canvas" in {
      val line: Line = Line((20, 20), (40, 40))
      canvas withinCanvas line should be(false)

      val elements = Line renderLines(line.from, line.to)
      canvas withinCanvas elements should be(Left("element Line(Position(20,20),Position(20,20)) is positioned outside of the canvas"))
    }

    "places the element if it is inside the canvas boundaries" in {
      val line = Line((1, 1), (1, 2))
      canvas withinCanvas line should be(true)
    }
  }



}
