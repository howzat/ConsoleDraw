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

  implicit def positionOrdering: Ordering[Position] = Ordering.fromLessThan{ case (p1, p2) =>
    (p1.y < p2.y) || (p1.x < p2.x)
  }

}

case class Position(x:Int, y:Int) {

  def > (p:Position) = x > p.x && y > p.y
  def < (p:Position) = x < p.x && y < p.y

  def north() = copy(y=y-1)
  def northEast() = copy(x+1, y-1)
  def east() = copy(x=x+1)
  def southEast() = copy(x+1,y+1)
  def south() = copy(y=y+1)
  def southWest() = copy(x-1,y+1)
  def west() = copy(x=x-1)
  def northWest() = copy(x-1,y-1)

  lazy val neighbours: List[Position] = {
    List(north(), east(), south(), west())
  }
}