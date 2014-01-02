package com.howzat.draw.model

case class Position(x:Int, y:Int) {

  def > (p:Position) = x >= p.x && y >= p.y
  def < (p:Position) = x < p.x && y < p.y
}