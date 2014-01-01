package com.howzat.draw.commands

import com.howzat.draw._
import com.howzat.draw.model.Position

trait Command
case class Quit() extends Command
case class NewCanvas(width:Int, height:Int) extends Command
case class ApplyFill(position:Position, colour:String) extends Command
case class DrawLine(topLeft:Position, bottomRight:Position) extends Command
case class DrawRectangle(topLeft:Position, bottomRight:Position) extends Command
