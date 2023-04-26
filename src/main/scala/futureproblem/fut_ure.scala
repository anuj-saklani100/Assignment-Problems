package futureproblem
import scala.concurrent.Future
import scala.util.{Try,Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global
object fut_ure extends App{

  // Future is used for the computation
  // for running the asynchronous block of code
  /// means if a piece of code is taking a longer duration so instead of blocking another set of code for that time
  // let that code run at the background and work with another set of code
  var fut1:Future[Int]= Future{
    1219218    // dummy number (PS: My college roll number)
  }
  var fut2:Future[String]= Future{
    "Anuj Saklani Completed the Assigmnent"   // returning a random string
  }
 // using zip to combine both future
  var result:Future[(Int,String)]=fut1.zip(fut2).flatMap{
        // pattern matching concept
    case (int_stuff, string_stuff)=>
      Future.successful((int_stuff,string_stuff))  // if we hit a success then return a tuple
  }.recover{  // or else handle the exception
    case (ex) =>
      println(s"The error message is:-  ${ex.getMessage}")
      throw ex
  }
  result.onComplete{   // if a success then return the result which is basically a tuple of (int,string)
    case Success(res)=>
      println(s"The combine result of two future is: $res")
    case Failure(exe)=> // print the error message
      println(s"The code fails with message: ${exe.getMessage}")
  }

}
