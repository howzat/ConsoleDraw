package com.howzat.model

trait Layout {

  def placeElement(e:Element, canvas:Canvas) : Either[String, Canvas]
}

object BasicLayout extends Layout {

  def placeElement(e: Element, canvas: Canvas) = ???
}