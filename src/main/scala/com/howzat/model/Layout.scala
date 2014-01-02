package com.howzat.model

import com.howzat.CanvasResult



object Layout {
  def default = new BasicLayout()
}

trait Layout {

  def placeElement(e:Element, canvas:Canvas) : CanvasResult

  def within(e:Element, c:Canvas) = {

  }
}

class BasicLayout() extends Layout {

  def placeElement(e: Element, canvas: Canvas) = {




    Right(canvas)
  }
}