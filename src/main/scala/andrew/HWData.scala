package andrew

import breeze.linalg._
import breeze.numerics.I
import breeze.stats._

import scala.io.Source
import scala.reflect.ClassTag

object HWData {
  
  val DataDirectory = "data/"
  val fileName = "height_weights.csv"

  def load:HWData = {
    val file = Source.fromFile(DataDirectory + fileName)
    val lines = file.getLines.toVector // Vector[String]
    val words = lines.map { _.split(',') } // Vector[Array[String]]

    def fromList[T:ClassTag](index:Int, converter:(String => T)):DenseVector[T] =
      DenseVector.tabulate(lines.size) { row => converter(words(row)(index)) }

    val genders = fromList(1, elem => elem.replace("\"", "").head)
    val weights = fromList(2, elem => elem.toDouble)
    val heights = fromList(3, elem => elem.toDouble)
    val reportedWeights = fromList(4, elem => elem.toDouble)
    val reportedHeights = fromList(5, elem => elem.toDouble)

    val maleVector = DenseVector.fill(genders.length)('M') // DenseVector[Char]
    val isMale = genders :== maleVector // goes componentwise through 'genders' and creates DenseVector[Boolean]
    val isMaleTransformedToDouble = I(isMale) // changes 'true' to 1.0 and 'false' to 0.0 - DenseVector[Boolean]
    val numOfMen = sum(isMaleTransformedToDouble)
    println(s"There are $numOfMen men is data set")
    val maleHeightsSlice = heights(isMale) // 'SliceVector[Int, Double] - just a view, map from indices to values
    val maleHeights = maleHeightsSlice.toDenseVector
    val generalHeightMean = mean(heights)
    val maleHeightMean = mean(heights(isMale))
    val femaleHeightMean = mean(heights(!isMale))
    println(s"mean height: $generalHeightMean")
    println(s"mean male height: $maleHeightMean")
    println(s"mean female height: $femaleHeightMean")
    // Calculate how many men overestimated their height
    val overReportedHeightMask = (reportedHeights :> heights).toDenseVector
    val numOfMenOverReportingHeight = sum(I(overReportedHeightMask :& isMale)) // Notice elementwise AND operator
    println(s"Number of men overreporting height: $numOfMenOverReportingHeight")

    new HWData(weights, heights, reportedWeights, reportedHeights, genders)
    //new HWData(weights, heights, genders)
  }

}

class HWData(
  val weights:DenseVector[Double],
  val heights:DenseVector[Double],
  val reportedWeights:DenseVector[Double],
  val reportedHeights:DenseVector[Double],
  val genders:DenseVector[Char]
) {

  val npoints = heights.length
  require(weights.length == npoints)
  require(genders.length == npoints)
//  require(reportedWeights.length == npoints)
//  require(reportedHeights.length == npoints)
//
//  lazy val rescaledHeights:DenseVector[Double] = (heights - mean(heights)) / stddev(heights)
//
//  lazy val rescaledWeights:DenseVector[Double] = (weights - mean(weights)) / stddev(weights)
//
//  lazy val featureMatrix:DenseMatrix[Double] =
//    DenseMatrix.horzcat(
//      DenseMatrix.ones[Double](npoints, 1),
//      rescaledHeights.toDenseMatrix.t,
//      rescaledWeights.toDenseMatrix.t
//    )
//
//  lazy val target:DenseVector[Double] = genders.values.map { gender => if(gender == 'M') 1.0 else 0.0 }

  override def toString:String = s"andrew.HWData has $npoints rows"



}

