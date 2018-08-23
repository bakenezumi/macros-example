package example.enum.v2

sealed trait AorB extends Enum {type V = String}

object AorB extends EnumCompanion[AorB] {
  case object A extends AorB {val value = "A"}
  case object B extends AorB {val value = "B"}
  //def apply(v: String) = applyEnum(v)
}
