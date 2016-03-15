package andrew

import breeze.linalg._
import breeze.numerics._
import breeze.plot._
import breeze.stats.regression._
import org.jfree.chart.annotations.XYTextAnnotation
import org.jfree.chart.axis.NumberTickUnit
import org.jfree.chart.plot.ValueMarker
class Plots {
  def mySimplePlot = {
    val x = linspace(-1.0, 2.0, 401)
    val y = x :* x
    val z = x :/ (x :+ 3.0)
    val fig = Figure("Piece of parabola") // creates an empty Java Swing window
    val plt = fig.subplot(0)
    fig.refresh()
    plt.yaxis.setTickUnit(new NumberTickUnit(0.5))
    plt.ylim = (-0.6, 3.0)
    plt += plot(x, y, name = "Parabola")
    plt += plot(x, z, name = "Hyperbola")
    plt.plot.addRangeMarker(new ValueMarker(0.0)) // horizontal axis
    plt.plot.addDomainMarker(new ValueMarker(0.0)) // vertical axis
    plt.xlabel = "x"
    plt.ylabel = "y"
    plt.legend = true
  }

  def subplotExample = {
    val data = HWData.load

    val fig = new Figure("Subplot example")

    // upper subplot
    var plt = fig.subplot(2, 1, 0) // nums of rows, nums of columns, plot index
    plt += plot(data.heights, data.weights, '.')

    // lower subplot
    plt = fig.subplot(2, 1, 1)
    plt += plot(data.heights, data.reportedHeights, '.', colorcode="red")
  }

  def scatterExample = {
    val fig = Figure("Scatter example")

    val plt = fig.subplot(0)

    val xs = linspace(0.0, 1.0, 100)
    val sizes = 0.025*rand(100)
    //val colorPalette = new GradientPaintScale(0.0, 1.0, PaintScale.MaroonToGold)
    val colorPalette = new GradientPaintScale(0.0, 1.0, PaintScale.RedToGreen)
    val colors = DenseVector.rand(100).mapValues(colorPalette)

    plt += scatter(xs, xs :^ 2.0, sizes.apply, colors.apply)
    fig.refresh()
  }

  def leastSquaresFit = {
    val data = HWData.load
    val heights = data.heights
    val weights = data.weights

    val dataForLeastSquares = DenseMatrix.horzcat(DenseMatrix.ones[Double](data.npoints, 1), heights.toDenseMatrix.t)
    val leastSquaresResult = leastSquares(dataForLeastSquares, weights)
    val leastSquaresCoefficients = leastSquaresResult.coefficients // DenseVector[Double]

    // Say we wish to have coefficients separately
    val Array(a, b) = leastSquaresCoefficients.toArray
    println(s"Coefficients: a = $a, b = $b")

    val label = f"weight = ${leastSquaresCoefficients(0)}%.4f + ${leastSquaresCoefficients(1)}%.4f *height"
    println("Least squares result: ")
    println(label)
    println(s"residuals = ${leastSquaresResult.rSquared}")
    val heightPoints = linspace(min(heights), max(heights), 200)
    val fittedWeights = leastSquaresCoefficients(0) :+ (leastSquaresCoefficients(1):*heightPoints)

    val fig = Figure("Height vs. weight")
    val plt = fig.subplot(0)
    plt += plot(heights, weights, '+', colorcode="black")
    plt += plot(heightPoints, fittedWeights, colorcode="red")
    plt.plot.addAnnotation(new XYTextAnnotation(label, 175.0, 105.0))
  }
}
