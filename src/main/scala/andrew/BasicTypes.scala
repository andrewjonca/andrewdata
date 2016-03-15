package andrew

import breeze.linalg.{DenseMatrix, DenseVector, linspace}

/**
  * Unfortunately, vectors and matrices are treated as separate types. MATLAB does it better
  * and I believe, the same could be done in Breeze
  *
  * Breeze vectors and matrices are mutable
  * Breeze vectors are column vectors when viewed as a matrix
  */
class BasicTypes {
  def showVectorType = {
    val v1 = DenseVector(1.0, 2.0)
    println(s"v1 = $v1")
    val v2 = v1 * 2.4
    println(s"v2 = $v2")
    val v3 = 2.4 * v1
    println(s"v3 = $v3")
    val v4 = v1 + v2
    println(s"v4 = $v4")
    val res1 = v1 dot v2
    println(s"dot product of $v1 with $v2 = $res1")
    val v5 = DenseVector.ones[Double](3)
    println(s"v5 = $v5")
    val v6 = DenseVector.zeros[Int](3)
    println(s"v6 = $v6")
    val v7 = DenseVector.rand(3)
    println(s"v7 = $v7")
    val v8 = linspace(0.0, 12.0, 5)
    println(s"v8 = $v8")
  }

  def showMatrixType = {
    val m1 = DenseMatrix((1, 2, 3), (4, 5, 6))
    println(s"\nm1(1,0) = ${m1(1,0)}; m1 = \n$m1")
    val m2 = DenseMatrix((1, 2), (3, 4), (5, 6))
    println(s"\nm2 = \n$m2")
    val m3 = m1 * m2 // Matrix multiplication
    println(s"\nm3 = \n$m3")
    val m4 = m1.t
    println(s"\nm4 = \n$m4")

    val v1 = DenseVector(1, 2)
    val m5 = m2 * v1 // Matrix mapping a vector (or mutiplication of two matrices)
    println(s"\n$m2* $v1 = $m5")

    val m6 = DenseMatrix.ones[Int](2, 1)
    val m7 = DenseMatrix.horzcat(m6, m1)
    println(s"\nm7: \n$m7")
  }

  // Most operations similar to those present for Scala lists
  def extraProperties = {
    // 'tabulate' method lets us construct vectors and matrices from functions:
    val v1 = DenseVector.tabulate(3)(j => j + 1)
    println(s"DenseVector.tabulate(3)(j => j + 1) = $v1")
    val v2 = DenseVector.tabulate(3)(_.toDouble)
    println(s"DenseVector.tabulate(3)(_.toDouble) = $v2")
    val v3 = DenseVector.tabulate(3) (j => 5.0 * j)
    println(s"DenseVector.tabulate(3)(j => 5.0 * j) = $v3")
    val v4 = DenseVector.fill(4)('M')
    println(s"DenseVector.fill(4)('M') = $v4")
    val v5 = DenseVector.tabulate(10)(j => 2*j + 1)
    val v6 = v5 :> 3
    val v7 = v5 :< 12
    val v8 = (v6 :& v7).toDenseVector
    println(s"v8 = $v8")

    val m1 = DenseMatrix((1,2,3,4), (5,6,7,8), (9,0,1,2))
    val v9 = m1(::, 2)
    println(s"The third column of matrix\n$m1\nis\n$v9")
  }
}
