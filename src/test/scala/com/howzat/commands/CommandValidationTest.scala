package com.howzat.draw.commands

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position
import com.howzat.io.InputValidation

@RunWith(classOf[JUnitRunner])
class CommandValidationTest extends FreeSpec with ShouldMatchers {

  val validation = new CommandValidation(new InputValidation)

  "unrecognised commands should be rejected" in {
    case class Foo() extends Command
    validation isValid ( Foo() ) should be (Left("unrecognised command Foo()"))
  }

  "NewCanvas command validation" - {

    "should fail when either width or hieght is 0" in {
      validation isValid (NewCanvas(0, 0)) should be(Left("param 'height' must be greater than zero, param 'width' must be greater than zero"))
      validation isValid (NewCanvas(1, 0)) should be(Left("param 'height' must be greater than zero"))
      validation isValid (NewCanvas(0, 1)) should be(Left("param 'width' must be greater than zero"))
      validation isValid (NewCanvas(-1, 1)) should be(Left("param 'width' must be greater than zero"))
      validation isValid (NewCanvas(1, -1)) should be(Left("param 'height' must be greater than zero"))
    }

    "should pass the validated result if both height and width are > 0" in {
      validation isValid (NewCanvas(1, 1)) should be(Right(NewCanvas(1, 1)))
    }
  }

  "Draw Line command validation" - {

    "should fail is the line is not horizontal" in {
      val diagonal = DrawLine(Position(1, 1), Position(10, 10))
      validation isValid (diagonal) should be(Left("lines must be drawn either vertically or horizontally"))
    }

    "should fail is the line has 0 length" in {
      val lengthZero = DrawLine(Position(1, 1), Position(1, 1))
      validation isValid (lengthZero) should be(Left("line length cannot be zero"))
    }

    "should fail if any position contains negative numbers" in {
      val lengthZero = DrawLine(Position(-1,10), Position(-10, 10))
      validation isValid (lengthZero) should be(Left("param 'top left x' must be greater than zero, param 'bottom right x' must be greater than zero"))
    }
    
    "should fail if any position contains 0" in {
      val startsAtZero = DrawLine(Position(0,10), Position(10, 10))
      validation isValid (startsAtZero) should be(Left("param 'top left x' must be greater than zero"))
    }

    "should pass lines that are vertical" in {
      val vertical = DrawLine(Position(1, 1), Position(1, 10))
      validation isValid (vertical) should be(Right(DrawLine(Position(1, 1), Position(1, 10))))
    }

    "should pass lines that are horizontal" in {
      val horizontal = DrawLine(Position(1, 1), Position(10, 1))
      validation isValid (horizontal) should be(Right(DrawLine(Position(1, 1), Position(10, 1))))
    }
  }
}
