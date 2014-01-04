package com.howzat.draw.model

object Position {

  implicit def tupleToPosition(t:Tuple2[Int, Int]): Position = {
   Position(t._1, t._2)
 }

  implicit def tupleListToPositionList[S <: Seq[Tuple2[Int, Int]]](t:S): List[Position] = {
    t map { s=> Position(s._1, s._2) } toList
  }

  implicit def positionListToTuple2List[S <: Seq[Position]](t:S): List[Tuple2[Int, Int]] = {
    t map { s=> (s._1, s._2) } toList
  }

  implicit def positionToTuple(p:Position): Tuple2[Int, Int]= {
    (p.x,p.y)
  }
}

case class Position(x:Int, y:Int) {

  def > (p:Position) = x >= p.x && y >= p.y
  def < (p:Position) = x < p.x && y < p.y
}