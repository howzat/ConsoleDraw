package com.howzat.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position


@RunWith(classOf[JUnitRunner])
class DefaultLayoutTest extends FreeSpec with ShouldMatchers {

  val rectangle = Rectangle(Position(20, 20), Position(40, 40))


  "Using the default layout" - {

    "fails if the element is outside the canvas" in {
      val canvas: Canvas = Canvas(10, 10)
      Layout.default placeElement(rectangle, canvas) should be ( Left("cannot place an element outside of the canvas") )
    }

    "places the element if it is inside the canvas boundaries" in {
      val canvas: Canvas = Canvas(100, 100)
      Layout.default placeElement(rectangle, canvas) should be ( Right() )
    }
  }

}
