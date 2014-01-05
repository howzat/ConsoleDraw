package com.howzat.io

import org.scalatest.FreeSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.Canvas

@RunWith(classOf[JUnitRunner])
class ElementPrinterTest extends FreeSpec with ShouldMatchers  {

  val printer = ElementPrinter()

  "Canvas Printer " - {

    "prints empty canvas" in {

      val output =
        """------
          ^|oooo|
          ^|oooo|
          ^|oooo|
          ^|oooo|
          ^------""".stripMargin('^')

      val s: String = printer printCanvas Canvas(4, 4, Nil)
      s should be (output)
    }
  }


}
