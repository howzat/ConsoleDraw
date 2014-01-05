package com.howzat.model

import org.scalatest.FreeSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class FillTest extends FreeSpec with ShouldMatchers {


  "rendering a fillArea " - {

    "1 x 2" in {
      val canvas = Canvas(1, 2)
      (FillPoint renderPositions((1, 1), canvas) sorted) should be(List(Position(1, 1), Position(1, 2)))
    }

    "2 x 1" in {
      val canvas = Canvas(2, 1)
      (FillPoint renderPositions((1, 1), canvas) sorted) should be(List(Position(1, 1), Position(2, 1)))
    }

    "3 x 3" in {
      val canvas = Canvas(3, 3)
      (FillPoint renderPositions((1, 1), canvas) sorted ) should be(
         List(
               Position(1, 1), Position(2, 1), Position(3, 1),
               Position(1, 2), Position(2, 2), Position(3, 2),
               Position(1, 3), Position(2, 3), Position(3, 3))
      )
    }
  }
}
