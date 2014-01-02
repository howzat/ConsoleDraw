package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import com.howzat.model.Canvas
import com.howzat.draw.model.Position


@RunWith(classOf[JUnitRunner])
class CanvasTest extends FreeSpec with ShouldMatchers {

  private val rectangle: Rectangle = Rectangle(Position(20, 20), Position(20, 20))
  private val line: Line = Line(Position(20, 20), Position(20, 20))
  private val fill: FillPoint = FillPoint(Position(20, 20), "x")

  "Canvas of heigth 100 and width 100" - {

    val canvas = Canvas(100, 100)

    "can report given height and width" in {
      canvas.height should be(100)
      canvas.width should be(100)
    }

    "can add a Rectangle element" in {
      val canvas = Canvas(100, 100) + rectangle
      canvas.elements should be(Vector(rectangle))
    }

    "can add a Line element" in {
      val canvas = Canvas(100, 100) + line
      canvas.elements should be(Vector(line))
    }

    "can add a Fill element" in {
      val canvas = Canvas(100, 100) + fill
      canvas.elements should be(Vector(fill))
    }

    "can add both a Line and Fill elements" in {
      val canvas = Canvas(100, 100) + line + fill
      canvas.elements should be(Vector(fill, line))
    }
  }
}
