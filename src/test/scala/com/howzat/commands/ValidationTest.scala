package com.howzat.draw.commands

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.howzat.draw.model.Position
import com.howzat.io.InputValidation

@RunWith(classOf[JUnitRunner])
class ValidationTest extends FreeSpec with ShouldMatchers {

  val validation = new Validation(new InputValidation)

  "NewCanvas command validation" - {

    "should fail when either width or hieght is 0" in {
      validation isValid (NewCanvas(0, 0)) should be(Left("param 'height' cannot be zero, param 'width' cannot be zero"))
      validation isValid (NewCanvas(1, 0)) should be(Left("param 'height' cannot be zero"))
      validation isValid (NewCanvas(0, 1)) should be(Left("param 'width' cannot be zero"))
    }

    "should pass the validated result if both height and width are > 0" in {
      validation isValid (NewCanvas(1, 1)) should be(Right(NewCanvas(1, 1)))
    }
  }

  "Draw Line command validation" - {

    "should fail is the line is not horizontal" in {
      val diagonal = DrawLine(Position(0, 0), Position(10, 10))
      validation isValid (diagonal) should be(Left("lines must be drawn either vertically or horizontally"))
    }

    "should fail is the line is not vertical" in {
      val diagonal = DrawLine(Position(0, 0), Position(10, 10))
      validation isValid (diagonal) should be(Left("lines must be drawn either vertically or horizontally"))
    }
  }


}
