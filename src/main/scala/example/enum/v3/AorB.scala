package example.enum.v3

sealed trait AorB extends Enum {type V = String}

object AorB extends EnumCompanion[AorB] {
  case object A extends AorB {
    override val value = "A"
  }
  case object B extends AorB {
    override val value = "B"
  }
  def apply(v: String) = applyEnum(v)
  validateEnum
}
