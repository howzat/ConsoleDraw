package com.howzat.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position


@RunWith(classOf[JUnitRunner])
class DefaultLayoutTest extends FreeSpec with ShouldMatchers {

  val layout: BasicLayout = Layout.default
  val rectangle = Rectangle(Position(0, 0), Position(10, 20))

  "Using the default layout" - {

    "should fail to place elements that wont fit horizontally" in {
      val r = Rectangle(Position(10, 10), Position(20, 20))
      layout fitsHorizontally( rectangle , Canvas(10, 10)) should be (Left("element is too wide, or positined too far to the right to fit on canvas"))
      layout fitsHorizontally( r , Canvas(20, 200)) should be (Left("element is too wide, or positined too far to the right to fit on canvas"))
    }

    "should fail to place elements that wont fit vertically" in {
      val r = Rectangle(Position(10, 10), Position(20, 20))
      layout fitsVertically ( rectangle , Canvas(200, 10)) should be (Left("element is too tall, or positined too far down to fit on canvas"))
      layout fitsVertically( r , Canvas(20, 20)) should be (Left("element is too tall, or positined too far down to fit on canvas"))
    }

    "fails if the element is outside the canvas" in {
      val canvas = Canvas(20, 20)
      layout positionedWithinCanvas(Rectangle(Position(20, 20), Position(30, 30)), canvas) should be ( Left("element is too big, or positioned outside of the canvas") )
    }

    "fails to place if the element is outside the canvas" in {
      val canvas = Canvas(10, 10)
      layout placeElement(Rectangle(Position(20, 20), Position(40, 40)), canvas) should be ( Left("element is too big, or positioned outside of the canvas") )
    }

    "places the element if it is inside the canvas boundaries" in {
      val canvas: Canvas = Canvas(100, 100)
      layout placeElement(rectangle, canvas) should be ( Right(Canvas(100,100,Vector(rectangle))) )
    }
  }
}
