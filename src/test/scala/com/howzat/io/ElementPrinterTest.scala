package com.howzat.io

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.{Element, Empty, HBorder, Canvas}
import com.howzat.draw.model.Position._

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

      val s: String = printer printCanvas (Canvas(4, 4, Nil))
      println(s"'$s'")
      s should be (output)
    }
  }


}
