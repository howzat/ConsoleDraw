package com.howzat.io

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.Canvas

@RunWith(classOf[JUnitRunner])
class PrinterTest extends FreeSpec with ShouldMatchers  {


  "Canvas Printer " - {

    "prints empty canvas" in {

      val output =
              """
                ^--------------------
                ^|                  |
                ^|                  |
                ^|                  |
                ^|                  |
                ^|                  |
                ^--------------------
              """.stripMargin('^')

      Printer printCanvas (Canvas(20,4)) should be (output)
    }
  }
}
