package com.howzat.model

import com.howzat.draw.model.Position
import scala._

object FillPoint {

  def renderPositions(clickPoint: Position, canvas: Canvas): List[Position] = {

    var searched: Vector[Position] = Vector.empty
    var found: List[Position] = List.empty

    def available(position: Position) {
      if(canvas.withinCanvas(position) && !(searched contains position)) {
        searched = position +: searched
        canvas.elementMap get position map {
          e =>
            if(e.isInstanceOf[Empty]) {
              found = position +: found
              position.neighbours map (available(_))
            }
        }
      }
    }

    available(clickPoint)
    found  
  }


  def renderFillArea(clickPoint: Position, colour: String, canvas: Canvas): List[Element] = {
    renderPositions(clickPoint, canvas) map (FillPoint(_, colour))
  }
}


case class FillPoint(p: Position, colour: String) extends SinglePointElement(p)