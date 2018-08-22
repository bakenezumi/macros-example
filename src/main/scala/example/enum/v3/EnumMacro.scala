package example.enum.v3

import language.experimental.macros
import scala.reflect.macros.blackbox.Context

object EnumMacro {
  def p(msg: String): Unit = macro pImpl
  def pImpl(c: Context)(msg: c.Expr[String]) = {
    import c.universe._
    q"""
        println("Hello, " + ${msg})
      """
  }
}
