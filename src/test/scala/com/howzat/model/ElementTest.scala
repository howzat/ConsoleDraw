package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class ElementTest extends FreeSpec with ShouldMatchers {

  val rectangle      = Rectangle(Position(20, 20), Position(40, 80))
  val fill           = FillPoint(Position(30, 30), "c")
  val horizontalLine = Line(Position(40, 40), Position(40, 90))
  val verticalLine   = Line(Position(40, 40), Position(90, 40))


  s"$rectangle" - {

    "should have height 20" in {
      rectangle.height should be(60)
    }

    "should have width 60" in {
      rectangle.width should be(20)
    }

    "should have position" in {
      rectangle.topLeft should be(rectangle.topLeft)
      rectangle.bottomRight should be(rectangle.bottomRight)
    }
  }

  s"$horizontalLine" - {

    "should have height 1" in {
      horizontalLine.height should be(50)
    }

    "should have width 1" in {
      horizontalLine.width should be(1)
    }

    "should have position" in {
      horizontalLine.topLeft should be(horizontalLine.topLeft)
      horizontalLine.bottomRight should be(horizontalLine.bottomRight)
    }
  }

  s"$verticalLine" - {

    "should have height 1" in {
      verticalLine.height should be(1)
    }

    "should have width 1" in {
      verticalLine.width should be(50)
    }

    "should have position" in {
      verticalLine.topLeft should be(verticalLine.topLeft)
      verticalLine.bottomRight should be(verticalLine.bottomRight)
    }
  }

  s"$fill" - {

    "should have height 1" in {
      fill.height should be(1)
    }

    "should have width 1" in {
      fill.width should be(1)
    }

    "should have position" in {
      fill.topLeft should be(fill.bottomRight)
      fill.bottomRight should be(fill.topLeft)
    }
  }

}
