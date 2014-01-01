package com.howzat.model

import com.howzat.draw.model.Position

trait Element {

}

case class Line(topLeft:Position, bottomRight:Position) extends Element
case class Fill(position:Position, colour:String) extends Element
case class Rectangle(topLeft:Position, bottomRight:Position) extends Element



