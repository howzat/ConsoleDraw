package com.howzat.draw

import com.howzat.draw.commands.Command
import com.howzat.io.{InputValidation, InputParser}


object CanvasMain extends App {

  def prompt() = print("Enter command:" )

  val parser   = new InputParser(new InputValidation)
  var canvas   = None

  prompt()

  while(true) {
    val line = Console.readLine trim()
    parser parse line map (execute(_, canvas)) getOrElse unknownCommand(line)
  }

  def execute(cmd:Either[String, Command], state:Option[Canvas]) = {
    cmd match {
      case Left(errors) => output(errors)
      case Right(cmd)   => output(cmd toString)
    }
  }

  def output(cmd: String) {
    println(s"$cmd")
    prompt()
  }

  def unknownCommand(line: String) {
    println(s"'$line' is not recognised as a valid command")
    prompt()
  }
}