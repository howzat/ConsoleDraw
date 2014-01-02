package com.howzat

import com.howzat.model.{Canvas, Element, Layout}
import java.util.concurrent.atomic.{AtomicReference, AtomicBoolean}

class DrawingSession(layout:Layout) {
  
  var canvas:Option[Canvas] = None
  
  def placeElement(element: Element) : CanvasResult = {
    Left("asassas")
  }

  def currentState: Option[Canvas] = {
    canvas map (_.copy())
  }

  def newCanvas(width:Int, height:Int) : Canvas = {
    canvas = Some(Canvas(width, height))
    currentState get
  }
}
