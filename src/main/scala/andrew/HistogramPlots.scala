package andrew

import breeze.linalg.DenseMatrix
import breeze.plot._

class HistogramPlots(val data: HWData) {

  val featureMatrix = DenseMatrix.horzcat(
    data.heights.toDenseMatrix.t,
    data.weights.toDenseMatrix.t,
    data.reportedWeights.toDenseMatrix.t
  )

  def plotSomeHistograms = {
    val fig = Figure("Heights histogram")
    var plt = fig.subplot(1, 3, 0) // nums of rows, nums of columns, plot index
    plt += hist(featureMatrix(::, 0), 15)
    plt.xlabel = "Heights with 15 histogram bins"

    plt = fig.subplot(1, 2, 1)
    plt += hist(featureMatrix(::, 0), 10)
    plt.xlabel = "Heights with 10 histogram bins"

    plt = fig.subplot(1, 3, 2)
    plt += hist(featureMatrix(::, 1), 10)
    plt.xlabel = "Weights with 10 histogram bins"
  }

}
