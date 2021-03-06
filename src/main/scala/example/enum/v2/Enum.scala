package example.enum.v2

import language.experimental.macros

trait Enum {
  type V
  val value: V
}

trait EnumCompanion[E <: Enum] {
  def unapply(e: E): Option[(E#V)] = Option(e.value)
  def applyEnum(v: E#V): E = macro EnumMacro.applyEnumImpl[E, E#V]
}
