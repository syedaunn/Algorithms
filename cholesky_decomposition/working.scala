package cholesky_decomposition;

object runner {
  def cholesky_factor(A:Array[Array[Double]], rows:Int, columns:Int):Array[Array[Double]]={
      var L = Array.ofDim[Double](rows,columns);
      var i=0;var j=0;var k =0;var sum=0.0;
      L(0)(0)=Math.sqrt(A(0)(0));
      for (i <- 0 until rows){
        for (j<- 0 until columns){
          if (i>j){
            sum=0;
            for (k<-0 until j)
              sum=sum+L(i)(k)*L(j)(k);
            L(i)(j)=1/L(j)(j)*(A(i)(j)-sum)
          }
          else if (i==j){
            sum=0;
            for (k<-0 until j)
              sum=sum+(L(j)(k)*L(j)(k));
            L(i)(j)=Math.sqrt(A(i)(j)-sum);
          }
          else if (j>i)
            L(i)(j)=0.0;
        }
      }
      return L;
  }
  
  def matrix_mul(A:Array[Array[Double]],B:Array[Array[Double]], rows:Int, columns:Int):Array[Array[Double]]={
    var sum=0.0;
    var res = Array.ofDim[Double](rows,columns);
    for (i<-0 until rows){
      for (j<-0 until columns){
        for (k<-0 until rows){
          sum=sum+A(i)(k)*B(k)(j);
        }
        res(i)(j)=sum;sum=0;
      }
    }
    return res;
  }
  
  def matrix_trans(A:Array[Array[Double]], rows:Int, columns:Int):Array[Array[Double]]={
    var res = Array.ofDim[Double](rows,columns);
     for (i<-0 until rows){
      for (j<-0 until columns){
        res(j)(i)=A(i)(j);
      }
    }
    return res;
  }
  
  def check_equal(A:Array[Array[Double]],B:Array[Array[Double]], rows:Int, columns:Int):Boolean={
    for (i<-0 until rows){
      for (j<-0 until columns){
        if (A(i)(j)!=B(i)(j))
          return false;
      }
    }
    return true;
  }
  
  def check_sim(A:Array[Array[Double]],B:Array[Array[Double]], rows:Int, columns:Int):Boolean={
    var W = Array.ofDim[Double](rows,columns);var Z = Array.ofDim[Double](rows,columns);var X = Array.ofDim[Double](rows,columns);
    W = cholesky_factor(A, rows, columns);
    Z = cholesky_factor(B, rows, columns);
    var Z_inv=matrix_inverse.invert(Z);
    X = matrix_mul(Z_inv, W, rows, columns);
    var X_trans=matrix_trans(X, rows, columns);
    W = matrix_mul(X_trans, A, rows, columns);
    W = matrix_mul(W, X, rows, columns);
    println(W.deep.mkString("\n"));
    return check_equal(B, W, rows, columns);
  }
  def main(args: Array[String]):Unit={
    println("Enter the dimensions of Arrays :");
    var dim = readLine();
    var rows = dim.slice(0, dim.indexOf("x")).toInt;
    var columns = dim.slice(dim.indexOf("x")+1, dim.length).toInt;
    var A = Array.ofDim[Double](rows,columns);
    A(0)(0)=8;A(0)(1)=5;A(0)(2)=0;A(1)(0)=4;A(1)(1)=8;A(1)(2)=4;A(2)(0)=0;A(2)(1)=4;A(2)(2)=8;
    var B = Array.ofDim[Double](rows,columns);
    B(0)(0)=4;B(0)(1)=2;B(0)(2)=0;B(1)(0)=2;B(1)(1)=4;B(1)(2)=2;B(2)(0)=0;B(2)(1)=2;B(2)(2)=4;
    println(check_sim(A,B,rows,columns));
  }
}
