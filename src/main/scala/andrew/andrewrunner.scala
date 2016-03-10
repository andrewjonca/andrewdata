package andrew

object andrewrunner extends App {
  println("Glorious project started!")
  val basicTypes = new BasicTypes
  basicTypes.showVectorType

  val data = HWData.load
  println(s"data: $data")
}
