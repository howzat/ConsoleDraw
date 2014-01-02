package com.howzat.model

import com.howzat.CanvasResult

trait Layout {

  def placeElement(e:Element, canvas:Canvas) : CanvasResult
}

object BasicLayout extends Layout {

  def placeElement(e: Element, canvas: Canvas) = {

    Right(canvas)
  }
}