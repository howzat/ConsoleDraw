package com.howzat

import com.howzat.model.{BasicLayout, Canvas, Element, Layout}
import java.util.concurrent.atomic.{AtomicReference, AtomicBoolean}

class DrawingSession(layout:Layout) {
  
  var canvas:Option[Canvas] = None
  
  def placeElement(element: Element) : CanvasEither = {
    canvas map {
      c => layout placeElement(element, c)
    } getOrElse Left("you must create a canvas before using draw commands e.g. 'C 10 10'")
  }

  def newCanvas(width:Int, height:Int) : Canvas = {
    canvas = Some(Canvas(width, height))
    canvas get
  }
}
