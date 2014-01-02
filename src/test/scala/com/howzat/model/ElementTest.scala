package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class ElementTest extends FreeSpec with ShouldMatchers {

  val rectangle = Rectangle(Position(20, 20), Position(40, 80))
  val fill      = FillPoint(Position(30, 30), "c")
  val line      = Line(Position(40, 40), Position(40, 90))


  s"$rectangle" - {

    "should have height 20" in {
      rectangle.height should be(60)
    }

    "should have width 60" in {
      rectangle.width should be(20)
    }
  }

  s"$line" - {

    "should have height 1" in {
      line.height should be(1)
    }

    "should have width 1" in {
      line.width should be(1)
    }

    "should have width position" in {
      line.position should be(line.position)
    }
  }

  s"$fill" - {

    "should have height 1" in {
      fill.height should be(1)
    }

    "should have width 1" in {
      fill.width should be(1)
    }

    "should have width position" in {
      fill.position should be(fill.position)
    }
  }

}
