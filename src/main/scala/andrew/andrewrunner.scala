package andrew

object andrewrunner extends App {
  println("Glorious project started!")
  val data = HWData.load
  val basicTypes = new BasicTypes
//  basicTypes.showVectorType
//  basicTypes.showMatrixType
  basicTypes.extraProperties
//  println(s"data: $data")
  //////////////////

  //val myPlots = new Plots
  //myPlots.mySimplePlot
  //myPlots.subplotExample
  //myPlots.scatterExample
  //myPlots.leastSquaresFit

  val histPlots = new HistogramPlots(data)
  //histPlots.plotSomeHistograms

}
