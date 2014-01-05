package com.howzat.model


object Rectangle {

  def renderPositions(topLeft:Position, bottomRight:Position) : List[Position] = {

    val top = Line renderPositions( topLeft, bottomRight.copy(y=topLeft.y) )
    val bottom = Line renderPositions( topLeft.copy(y =bottomRight.y) , bottomRight )
    val left = Line renderPositions( topLeft, bottomRight.copy(x=topLeft.x) )
    val right = Line renderPositions( topLeft.copy(bottomRight.x), bottomRight )

    top ++ bottom ++ left ++ right distinct
  }

  def renderRectangles(topLeft:Position, bottomRight:Position) : List[Element] = {
    renderPositions(topLeft, bottomRight) map {
      pos => Rectangle(pos, pos)
    }
  }
}

case class Rectangle(topLeft: Position, bottomRight: Position) extends Element