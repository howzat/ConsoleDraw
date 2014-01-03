package com.howzat.io

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import scala.util.{Failure, Success, Try}
import scala.util.matching.Regex
import com.howzat.draw.commands._
import com.howzat.draw.commands.DrawLine
import scala.Some
import com.howzat.draw.commands.DrawRectangle
import com.howzat.draw.commands.ApplyFill
import com.howzat.draw.commands.NewCanvas
import com.howzat.draw.model.Position


@RunWith(classOf[JUnitRunner])
class InputParserTest extends FreeSpec with ShouldMatchers {

  val parser = new InputParser(new InputValidation)

  "Parser returns None for any input it cannot match " in {
    parser parse "any old junk" should be(None)
  }

  "Parser matches 'C' for new Canvas " - {

    "with a positive width and height" in {
      parser parse ("C 10 10") should be(Some(Right(NewCanvas(10, 10))))
    }

    "will not match negative values" in {
      parser parse "C 10 -10" should be(None)
      parser parse "C -10 10" should be(None)
      parser parse "C 10 -10" should be(None)
    }

    "lowercase 'c' is not matched" in {
      parser parse "c 10 10" should be(None)
    }
  }

  "Parser accepts command 'R' for rectangle " - {

    "with placement co-ordinates of Top Left (0,0) and Bottom Right (10,10) " in {
      parser parse "R 0 0 10 10" should be(Some(Right(DrawRectangle(Position(0, 0), Position(10, 10)))))
    }
  }

  "Parser accepts command 'L' for line " - {

    "with placement co-ordinates of Top Left (0,0) and Bottom Right (0,10) " in {
      parser parse "L 0 0 0 10" should be(Some(Right(DrawLine(Position(0, 0), Position(0, 10)))))
    }
  }

  "Parser accepts command 'B' for fill " - {

    "with placement co-ordinates of (0,0) and colour of 'y' " in {
      parser parse "B 0 0 y" should be(Some(Right(ApplyFill(Position(0, 0), "y"))))
    }
  }

  "Parser accepts command 'Q' for quit " - {
    parser parse "Q" should be(Some(Right(Quit())))
  }
}


