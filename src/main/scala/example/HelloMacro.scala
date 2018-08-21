package example

import language.experimental.macros
import scala.reflect.macros.whitebox.Context

object HelloMacro {
  def p(msg: String): Unit = macro pImpl

  def pImpl(c: Context)(msg: c.Expr[String]) = {
    import c.universe._
    q"""
        println("Hello, " + ${msg})
      """
  }
}
