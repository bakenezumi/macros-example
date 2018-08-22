package example.enum.v3

trait Enum[T] {
  val value: T
  EnumMacro.p("aa")
}
