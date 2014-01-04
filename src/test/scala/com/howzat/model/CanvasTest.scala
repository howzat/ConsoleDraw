package com.howzat.model

import org.scalatest.{FreeSpec, FunSuite}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import scala._
import com.howzat.draw.model.Position._
import com.howzat.draw.model.Position

@RunWith(classOf[JUnitRunner])
class CanvasTest extends FreeSpec with ShouldMatchers {

  private val rectangle: Rectangle = Rectangle((20, 20), (20, 20))
  private val line     : Line      = Line((20, 20), (20, 20))
  private val fill     : FillPoint = FillPoint((30, 20), "x")

  "Canvas of heigth 100 and width 100" - {

    val canvas = Canvas(20, 4)

    "can report given height and width" in {
      canvas.height should be(4)
      canvas.width should be(20)
    }
  }


  "2 x 2 grid " - {

    val canvas: Canvas = Canvas(2, 2)
    
    "element grid with border" in {
      val elements = List(
         List(VBorder((0, 0)), VBorder((1, 0)), VBorder((2, 0)), VBorder((3, 0))),
         List(HBorder((0, 1)), Empty((1, 1)), Empty((2, 1)), HBorder((3, 1))),
         List(HBorder((0, 2)), Empty((1, 2)), Empty((2, 2)), HBorder((3, 2))),
         List(VBorder((0, 3)), VBorder((1, 3)), VBorder((2, 3)), VBorder((3, 3)))
       )
      canvas.grid should be(elements)
    }
    
  }
}
