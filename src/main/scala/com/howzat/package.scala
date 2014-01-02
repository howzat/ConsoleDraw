package com

import com.howzat.draw.commands.Command
import com.howzat.model.Canvas

package object howzat {

  type CommandEither = Either[String, Command]
  type CanvasEither = Either[String, Canvas]
}
