package com.howzat

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.{Layout, BasicLayout, Rectangle, Canvas}
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class DrawingSessionTest extends FreeSpec with ShouldMatchers {

  val rectangle = Rectangle(Position(20, 20), Position(20, 20))

  "Drawing session without an active canvas" - {

    val session = new DrawingSession(Layout.default)

    "fails attempts to place any elements" in {
      session placeElement (rectangle) should be(Left("you must create a canvas before using draw commands e.g. 'C 10 10'"))
    }

    "can create new canvas'" in {
      session newCanvas(100, 100) should be(Canvas(100, 100))
    }
  }


  "Drawing session with an active canvas" - {

    val session = new DrawingSession(Layout.default)
    session newCanvas(100, 100) should be(Canvas(100, 100))

    "can create new canvas'" in {
      session newCanvas(100, 100) should be(Canvas(100, 100))
    }

    "can place elements that are within the canvas boundaries" in {
      session placeElement (rectangle) should be(Right(Canvas(100, 100)))
    }


  }

}
