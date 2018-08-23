package example.enum.v2

import scala.reflect.macros.blackbox

object EnumMacro {
  def applyEnumImpl[E: c.WeakTypeTag, V: c.WeakTypeTag](c: blackbox.Context)(v: c.Expr[V]): c.Expr[E] = {
    import c.universe._
    val enumType = weakTypeOf[E]
    val subclasses = enumType.typeSymbol.asClass.knownDirectSubclasses
    val cases = subclasses.map { classSymbol =>
      val subclassName = classSymbol.name.toString
      CaseDef(
        Select(Ident(TermName(subclassName)), TermName("value")),
        EmptyTree,
        Ident(TermName(subclassName))
      )
    }.toList
    // v match {
    //   case Sub1.value => Sub1
    //   case Sub2.value => Sub2
    //   ...
    // }
    c.Expr[E](Match(Ident(TermName("v")), cases))
  }  
}
