package com.howzat

import org.scalatest.{FreeSpec, FunSuite}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.commands.{NewCanvas, ApplyFill, DrawLine, Command}
import com.howzat.draw.model.Position
import com.howzat.model.Canvas

@RunWith(classOf[JUnitRunner])
class ConsoleDrawingAppTest extends FreeSpec with ShouldMatchers {

  val app = new ConsoleDrawingApp(" ")

  "Console Drawing app" - {
    "prints a warning message when given an unrecognised command" in {
      case class Bar() extends Command
      app enterCommand( Bar() ) should be ("Bar() is and unrecognised command")
    }

    "prints a warning message when adding elements to an uninitialised canvas" in {
      app enterCommand( ApplyFill(Position(0,0), "c") ) should be ("Oops: you must create a canvas before using draw commands e.g. 'C 10 10'")
    }

    "prints an empty canvas when one is created" in {
      val output =
        """^----------------------
           ^|                    |
           ^|                    |
           ^|                    |
           ^|                    |
           ^----------------------""".stripMargin('^')

      app enterCommand( NewCanvas(20, 4) ) should be (output)
    }



  }
}
