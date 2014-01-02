package com.howzat.model

import com.howzat.draw.model.Position
import scala.collection.immutable.IndexedSeq
import scala.annotation.tailrec

case class Canvas(width: Int, height: Int, elements: Vector[Element] = Vector.empty) {

  val CanvasTopLeft  = Position(0, 0)
  val BorderValue: Int = 2
  val borderedWidth  = width + BorderValue
  val borderedHeight = height + BorderValue

  def +(e: Element) = {
    copy(elements = e +: elements)
  }

  private def emptyGrid: List[List[Element]] = {
    List.tabulate(height+2) {
      y => List.tabulate(width+2) {
        x =>
          val p = Position(x, y)
          if(isVerticalBorder(p)) VBorder(p)
          else if (isHorizontalBorder(p)) HBorder(p)
          else Empty(p)
      }
    }
  }




  def isLeftBorder(pos:Position) = (!isVerticalBorder(pos) && pos.x == 0)
  def isRightBorder(pos:Position) = (!isVerticalBorder(pos) && pos.x == borderedWidth-1)
  def isTopBorder(pos:Position) = (pos.y == 0 && pos.x >=0 && pos.x <= borderedWidth)
  def isBottomBorder(pos:Position)  = (pos.y == borderedHeight-1 && pos.x >=0 && pos.x <= borderedWidth)
  def isVerticalBorder(pos:Position) = (isTopBorder(pos) || isBottomBorder(pos))
  def isHorizontalBorder(pos:Position) = (isLeftBorder(pos) || isRightBorder(pos))
  def toElementGrid: List[List[Element]] = emptyGrid
  def topLeft = CanvasTopLeft


  def bottomRight = Position(width-1, height-1)

}


/*


val BorderValue: Int = 2
  val width = horizontalSpace + BorderValue
  val height = verticalSpace + BorderValue
  def hasLeftBorderAt(pos:Position) = (!isVerticalBorder(pos) && pos.x == 0)
  def hasRighBorder(pos:Position)  = (!isVerticalBorder(pos) && pos.x == width)
  
  private def isTopBorder(pos:Position) = (pos.y == 0 && pos.x >=0 && pos.x < width)
  private def isBottomBorder(pos:Position)  = (pos.y == height && pos.x >=0 && pos.x < width)
  def isVerticalBorder(pos:Position) = (isTopBorder(pos) || isBottomBorder(pos))
  /*
  List(
      List(VBorder(Position(0,0)), VBorder(Position(0,1)), VBorder(Position(0,2)), VBorder(Position(0,3))), 
      List(HBorder(Position(1,0)), Empty(Position(1,1)), Empty(Position(1,2)), Empty(Position(1,3))), 
      List(HBorder(Position(2,0)), Empty(Position(2,1)), Empty(Position(2,2)), Empty(Position(2,3))),
      List(HBorder(Position(3,0)), Empty(Position(3,1)), Empty(Position(3,2)), Empty(Position(3,3)))
  )
   */
 */