package com.howzat.model

case class Canvas(width:Int, height:Int, elements:Vector[Element]=Vector.empty) {

  def +(e:Element) = {
    copy(elements = e +: elements)
  }
}
