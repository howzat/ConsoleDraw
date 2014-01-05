package com.howzat.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class LineTest extends FreeSpec with ShouldMatchers {

  "rendering occupied positions" - {

    "can find rendered positions for a line" in {
      (Line renderLines((1, 1), (1, 3))) should be (
        List[Element](Line((1, 1),(1, 1)), Line((1, 2),(1, 2)), Line((1, 3),(1, 3)))
      )

      (Line renderLines((1, 1), (3, 1))) should be(
        List[Element](Line((1, 1),(1, 1)), Line((2, 1),(2, 1)), Line((3, 1),(3, 1)))
      )
    }
  }
}

