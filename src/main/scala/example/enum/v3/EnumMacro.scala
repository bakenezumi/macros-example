package example.enum.v3

import language.experimental.macros
import scala.reflect.macros.blackbox

object EnumMacro {
  def valid[T]: Unit = macro validImpl[T]
  
  def validImpl[T: c.WeakTypeTag](c: blackbox.Context) = {//(implicit wtt: c.WeakTypeTag[T]) = {
    import c.universe._
    c.abort(weakTypeOf[T].typeSymbol.pos, "エラー")
    q"""
        println("Hello")
      """
  }
}
