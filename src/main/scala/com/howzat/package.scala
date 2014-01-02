package com

import com.howzat.draw.commands.Command
import com.howzat.model.Canvas

package object howzat {

  type Result = Either[String, Command]
  type CanvasResult = Either[String, Canvas]
}
