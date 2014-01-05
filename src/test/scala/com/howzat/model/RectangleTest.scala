package com.howzat.model

import org.scalatest.FreeSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers

@RunWith(classOf[JUnitRunner])
class RectangleTest extends FreeSpec with ShouldMatchers {


  "rendering a rectangle" in {
    (Rectangle renderPositions ((1, 1), (3, 3))) should be (
        List(Position(1,1), Position(2,1), Position(3,1), Position(1,3), Position(2,3), Position(3,3), Position(1,2), Position(3,2))
    )

    (Rectangle renderRectangles ((1, 1), (3, 3))) should be (
        List(Rectangle(Position(1,1),Position(1,1)), Rectangle(Position(2,1),Position(2,1)), Rectangle(Position(3,1),Position(3,1)),
        Rectangle(Position(1,3),Position(1,3)), Rectangle(Position(2,3),Position(2,3)), Rectangle(Position(3,3),Position(3,3)),
        Rectangle(Position(1,2),Position(1,2)), Rectangle(Position(3,2),Position(3,2)))
     )
  }

}
