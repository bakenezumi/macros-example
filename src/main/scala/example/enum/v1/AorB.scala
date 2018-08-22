package example.enum.v1

sealed trait AorB {
   val value: String
}

object AorB {
  case object A extends AorB {
    override val value = "A"
  }
  case object B extends AorB {
    override val value = "B"
  }
  def apply(v: String): AorB = v match {
     case "A" => AorB.A
     case "B" => AorB.B
     // case _ => ...MatchError
   }
  def unapply(ab: AorB): Option[(String)] = Option(ab.value)
}
