package com

import com.howzat.commands.Command
import com.howzat.model.Canvas

package object howzat {

  type CommandEither = Either[String, Command]
  type CanvasEither = Either[String, Canvas]
}
