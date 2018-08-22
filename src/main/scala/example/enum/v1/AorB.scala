package example.enum.v1

abstract sealed class AorB {
   val value: String
}

object AorB {
  object A extends AorB {
    override val value = "A"
  }
  object B extends AorB {
    override val value = "B"
  }
  def apply(v: String): AorB = v match {
     case "A" => AorB.A
     case "B" => AorB.B
     // case _ => ...MatchError
   }
  def unapply(ab: AorB): Option[(String)] = Option(ab.value)
}
