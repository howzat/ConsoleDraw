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

  "rendering occupied positions" - {

    "can find rendered positions for a line" in {
      (Line renderPositions((1, 1), (1, 3))) should be (
        List[Element](Line((1, 1),(1, 1)), Line((1, 2),(1, 2)), Line((1, 3),(1, 3)))
      )

      (Line renderPositions((1, 1), (3, 1))) should be(
        List[Element](Line((1, 1),(1, 1)), Line((2, 1),(2, 1)), Line((3, 1),(3, 1)))
      )
    }
  }
}
