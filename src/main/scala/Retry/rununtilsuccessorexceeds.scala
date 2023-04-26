package Retry
// firstly i export the necessary utilities
import scala.util.{Try,Failure, Random, Success}
import scala.concurrent.duration.*
object rununtilsuccessorexceeds  extends App {
  // lets make a function that is taking two params, 1st is a function and 2nd is a counter of int type
  // generics we are using here
  // Basic idea of this code
  // -> it runs until success or when the attempts is less then the (attempts allowed)
def retry[A](code_block: =>A, counter:Int):A={
  var attempts=0   // initialize it with zero
    // from starting we assume that we have a failure, because we have to run a loop
  var result:Try[A] = Failure(new RuntimeException("Your number of attempts exceeded"))
  while(attempts<counter && result.isFailure){
    result= Try(code_block)  // the the block of code
    attempts+=1   // increase the attempt
    println("This is attempt number: " + attempts) // printing it for more clarity
    // here i am using pattern matching concept
    result match{
      case Success(_)=>
      case Failure(_)=>
// if the attempts is equal to counter menns our code is failed in every attempts so we throw a failure
        if(attempts==counter)result.failed.get
        else Thread.sleep(1000)   // Optional: let's take a pause for 1 second before retrying the block of code again
    }

  }
  result.get   // return the final result
}

  // now a function that is basically a block of code
def func():Int={
  // generating a random number
  var v=new Random
  // put a random number from 0 to 10
  var random_number=v.nextInt(10)
  // two cases in which it fails and not fails
  if(random_number<6)throw Exception("Oops!")
  else 100
}

  // checking the block of code with retry function
var ans= Try(retry(func(),3))

// Is success
println(ans.isSuccess)
// Is failure
println(ans.isFailure)

}
