package com.howzat.model

import org.scalatest.FreeSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers


@RunWith(classOf[JUnitRunner])
class PositionTest extends FreeSpec with ShouldMatchers {


  "Position can calculate" - {

    val p = Position(2, 2)
    "Northern neighbours position" in {
      p north() should be(Position(2, 1))
    }

    "North Eastern neighbours position" in {
      p northEast() should be(Position(3, 1))
    }

    "Eastern neighbours position" in {
      p east() should be(Position(3, 2))
    }

    "South Eastern neighbours position" in {
      p southEast() should be(Position(3, 3))
    }

    "Southern neighbours position" in {
      p south() should be(Position(2, 3))
    }

    "South Western neighbours position" in {
      p southWest() should be(Position(1, 3))
    }

    "Western neighbours position" in {
      p west() should be(Position(1, 2))
    }

    "North Western neighbours position" in {
      p northWest() should be(Position(1, 1))
    }
  }
}

