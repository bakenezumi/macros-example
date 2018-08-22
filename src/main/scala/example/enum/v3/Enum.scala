package example.enum.v3

import scala.language.experimental.macros

trait Enum[T] {
  val value: T
  Enum.valid[T]
}

object Enum {
  implicit def valid[T]: Unit = macro EnumMacro.validImpl[T]
}