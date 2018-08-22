package example.enum.v3

import scala.reflect.macros.blackbox

object EnumMacro {
  def applyEnumImpl[E: c.WeakTypeTag, V: c.WeakTypeTag](c: blackbox.Context)(v: c.Expr[V]): c.Expr[E] = {
    import c.universe._
    val enumType = weakTypeOf[E]
    val enumSymbol = enumType.typeSymbol
    val valueType = weakTypeOf[V]
    val subclasses: Set[c.universe.Symbol] = enumType.typeSymbol.asClass.knownDirectSubclasses
    if(subclasses.isEmpty)
      c.abort(c.enclosingPosition, s"subclasses of ${enumType.typeSymbol.fullName} is empty")
    val cases = subclasses.map { classSymbol: c.universe.Symbol =>
      val subclassName = classSymbol.name.toString
      val paramName = "value"
      CaseDef(
        q"${TermName(subclassName)}.${TermName(paramName)}",
        EmptyTree,
        q"${TermName(subclassName)}"
      )
    }.toList
    c.Expr[E](Match(Ident(TermName("v")), cases))
  }
}
