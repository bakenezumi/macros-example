package example.enum.v3

import scala.reflect.macros.blackbox

object EnumMacro {
  def applyEnumImpl[E: c.WeakTypeTag, V: c.WeakTypeTag](c: blackbox.Context)(v: c.Expr[V]): c.Expr[E] = {
    import c.universe._
    val enumType = weakTypeOf[E]
    val subclasses = enumType.typeSymbol.asClass.knownDirectSubclasses
    val valueParamName = "value"
    // if(subclasses.isEmpty)
    //   c.abort(c.enclosingPosition, s"subclasses of ${enumType.typeSymbol.fullName} is empty")
    val cases = subclasses.map { classSymbol =>
      val subclassName = classSymbol.name.toString
      CaseDef(
        Select(Ident(TermName(subclassName)), TermName(valueParamName)),
        EmptyTree,
        Ident(TermName(subclassName))
      )
    }.toList
    c.Expr[E](Match(Ident(TermName("v")), cases))
  }  
}
