package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class BordersTest extends FreeSpec with ShouldMatchers {

  import Borders._

  "Border for a 2 x 2 canvas" - {

    val borders = Borders(2, 2)

    "find vertical borders" in {
      borders isTopBorder ((0, 0)) should be(true)
      borders isTopBorder ((1, 0)) should be(true)
      borders isTopBorder ((2, 0)) should be(true)
      borders isTopBorder ((3, 0)) should be(true)
      borders isBottomBorder ((0, 3)) should be(true)
      borders isBottomBorder ((1, 3)) should be(true)
      borders isBottomBorder ((2, 3)) should be(true)
      borders isBottomBorder ((3, 3)) should be(true)
      borders isVerticalBorder ((0, 0)) should be(true)
      borders isVerticalBorder ((1, 0)) should be(true)
      borders isVerticalBorder ((2, 0)) should be(true)
      borders isVerticalBorder ((3, 0)) should be(true)
      borders isVerticalBorder ((0, 3)) should be(true)
      borders isVerticalBorder ((1, 3)) should be(true)
      borders isVerticalBorder ((2, 3)) should be(true)
      borders isVerticalBorder ((3, 3)) should be(true)
      borders isVerticalBorder ((1, 1)) should be(false)
    }

    "find horizontal borders" in {
      borders isLeftBorder ((0, 0)) should be(false)
      borders isLeftBorder ((0, 1)) should be(true)
      borders isLeftBorder ((0, 2)) should be(true)
      borders isLeftBorder ((0, 3)) should be(false)
      borders isRightBorder ((3, 0)) should be(false)
      borders isRightBorder ((3, 1)) should be(true)
      borders isRightBorder ((3, 2)) should be(true)
      borders isRightBorder ((3, 3)) should be(false)
      borders isHorizontalBorder ((0, 0)) should be(false)
      borders isHorizontalBorder ((0, 1)) should be(true)
      borders isHorizontalBorder ((0, 2)) should be(true)
      borders isHorizontalBorder ((0, 3)) should be(false)
      borders isHorizontalBorder ((3, 0)) should be(false)
      borders isHorizontalBorder ((3, 1)) should be(true)
      borders isHorizontalBorder ((3, 2)) should be(true)
      borders isHorizontalBorder ((3, 3)) should be(false)
      borders isHorizontalBorder ((1, 1)) should be(false)
    }
  }


  "Canvas of 10 x 10" - {

    val canvas = Canvas(10, 10)
    
    "should fail to place a position on the border" in {
      positionWithinCanvas((0,0), canvas) should be (false)
      positionWithinCanvas((1,0), canvas) should be (false)
      positionWithinCanvas((1,1), canvas) should be (true)
    }
    
    "should fail to place elements that wont fit horizontally" in {
      val tooWide = Line((1, 1), (11, 1))
      elementWithinCanvas(tooWide, canvas) should be(false)
    }

    "should fail to place elements that wont fit vertically" in {
      val toTall = Line((1, 1), (1, 11))
      elementWithinCanvas(toTall, canvas) should be(false)

      val elements = Line renderPositions(toTall.from, toTall.to)
      withinCanvas(elements, canvas) should be (Left("element Line(Position(1,11),Position(1,11)) is positioned outside of the canvas"))
    }

    "places single points at 1,1" in {
      val elements2 = Line renderPositions((1,1), (1,1))
      withinCanvas(elements2, canvas) should be (Right(canvas))
    }

    "fails if the element is outside the canvas" in {
      val line = Line((20, 20), (30, 30))
      elementWithinCanvas(line, canvas) should be(false)

      val elements = Line renderPositions((20, 20), (30, 30))
      withinCanvas(elements, canvas) should be (Left("element Line(Position(20,20),Position(20,20)) is positioned outside of the canvas"))
    }

    "fails to place if the element is outside the canvas" in {
      val line: Line = Line((20, 20), (40, 40))
      elementWithinCanvas(line, canvas) should be(false)

      val elements = Line renderPositions(line.from, line.to)
      withinCanvas(elements, canvas) should be (Left("element Line(Position(20,20),Position(20,20)) is positioned outside of the canvas"))
    }

    "places the element if it is inside the canvas boundaries" in {
      val line = Line((1,1), (1, 2))
      elementWithinCanvas(line, canvas) should be(true)
    }
  }
}