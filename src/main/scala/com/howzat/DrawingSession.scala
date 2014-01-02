package com.howzat

import com.howzat.model.{BasicLayout, Canvas, Element, Layout}
import java.util.concurrent.atomic.{AtomicReference, AtomicBoolean}

class DrawingSession(layout:Layout) {
  
  var canvas:Option[Canvas] = None
  
  def placeElement(element: Element) : CanvasResult = {
    canvas map {
      c => layout placeElement(element, c)
    } getOrElse Left("you must create a canvas before using draw commands e.g. 'C 10 10'")
  }

  def currentState: Option[Canvas] = {
    canvas map (_.copy())
  }

  def newCanvas(width:Int, height:Int) : Canvas = {
    canvas = Some(Canvas(width, height))
    currentState get
  }
}
