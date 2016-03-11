package andrew

import breeze.linalg.DenseVector

class BasicTypes {
  def showVectorType = {
    val v1 = DenseVector(1, 3, 4)
    println(s"v1 = $v1")
  }
}
