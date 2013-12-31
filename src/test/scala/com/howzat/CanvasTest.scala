package com.howzat.draw

import org.scalatest.{FreeSpec, FunSuite}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers


@RunWith(classOf[JUnitRunner])
class CanvasTest extends FreeSpec with ShouldMatchers {

  "Creating a canvas" - {

    "of height 1 and width 1" in {
      val canvas = Canvas(1, 1)
      canvas. height should be (1)
      canvas. width should be (1)
    }

//    "with negative dimensions should throw an IllegalArgumentException" in {
//      intercept[IllegalArgumentException] (Canvas(-1, 1))
//      intercept[IllegalArgumentException] (Canvas(1, -1))
//      intercept[IllegalArgumentException] (Canvas(-1, -1))
//    }
  }



}
