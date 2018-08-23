package example.enum.v2

sealed trait AorB extends Enum[String]

object AorB {
  case object A extends AorB {
    override val value = "A"
  }
  case object B extends AorB {
    override val value = "B"
  }
  def apply(v: String): AorB = v match {
     case A.value => AorB.A
     case B.value => AorB.B
     // case _ => ...MatchError
   }
  def unapply(ab: AorB): Option[(String)] = Option(ab.value)
}
