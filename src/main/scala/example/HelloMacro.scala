package example

import language.experimental.macros
import scala.reflect.macros.blackbox.Context

object HelloMacro {
  def hello(msg: String): Unit = macro helloImpl

  def helloImpl(c: Context)(msg: c.Expr[String]) = {
    import c.universe._
    msg.tree match {
      case Literal(Constant("A")) => q""" println("Hello, " + $msg) """
      case Literal(Constant("B")) => q""" println("こんにちは, " + $msg) """
      case _ => c.abort(c.enclosingPosition, "コンパイルエラー")
    }
  }
}
