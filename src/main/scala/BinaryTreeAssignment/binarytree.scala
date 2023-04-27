package BinaryTreeAssignment
import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.Option._
object binarytree extends App{
  // create a case class for building nodes
  // we are taking it as an Option type because it can efficiently handle the unsafe cases too without any crash
case class Node(data:Int,left:Option[Node] ,right:Option[Node])
object BT{
  def build(dep:Int):Option[Node]={
    // base case
    if(dep==0) None
    else{
      // left recursive call
      val left=build(dep-1)
      // right recursive call
      val right=build(dep-1)
      // backtracking the result to the upper side (Bottom Up fashion)
      Some(Node(dep,left,right))
    }
  }
}
// Simply a call to build a Binary tree having Number of nodes=> 2^dep -1
// So, if dep =3 , then nodes are 7 in out Binary tree
val d=BT.build(3)

// function to print the pre order Traversal of the Binary tree
// we are taking 1 parameter of node type wrapped in Option, and the return type is Unit because we just have to print the values
def preorderTraversal(node:Option[Node]):Unit={
  // I used pattern matching concept (Two cases handled very efficiently)
  node match{
    case Some(n)=>{
      print(s"${n.data} ")
      //recursive calls (left&right)
      preorderTraversal(n.left)
      preorderTraversal(n.right)
    }
    // default case (a kind of, when we hit to null just stop )
    case None=> // we left it as it is because it represents a null node
  }
}
  // function to find the height of the tree as well as in the same function we can calculate the number of leaf nodes
  // the return type of this functions is Int
  var leaf_nodes=0 // initially the counter is zero
  def height(node: Option[Node]): Int = {
    // Again i am using a pattern matching concept
    node match {
      case Some(n) => {
        // left and right recursive calls
        var left = height(n.left)
        var right = height(n.right)
        // I cleverly calculating the number of leaf node here by my observation
        // instead of making another function for it
        if(left==0&&right==0) leaf_nodes=leaf_nodes+1  // because leaf node has no child
        // formula i made from the observations
        val res= 1+Math.max(left,right)
             res
      }
      // hit a node return zero
      case None => 0// we will return a zero when we hit a null node
    }
  }

  // Now its the time to test my code
  var root=BT.build(4)  // building tree

  println("Pre Order traversal: ")
  preorderTraversal(root) // print the preorder traversal of the binary tree

  println("")
  println("height of the tree: "+height(root))  // height of the tree


  println("Number of leaf nodes in tree: "+ leaf_nodes)

  // New updates : Customized input list
  val my_list= ListBuffer[Option[Int]]()
  var input =""    // we cant take it as val because i have to variate it every time so it should be mutable
var len=0
  while(input!="stop"){
    input = readLine()
   if(input=="null"){ len+=1
     my_list+= None}
    else if(input!="stop") { len+=1
     my_list += Some(input.toInt)
    }
  }
  // just to check my list elements
  my_list.foreach {
    case Some(n)=> print(n + " ")
    case None =>
  }
  //println(my_list(0))
  println("")

  def Build_tree(start:Int,end:Int):Option[Node]={

    if(start>end)None
    else{
      val data = my_list(start)


      val left=Build_tree((2*start)+1,end)
      val right=Build_tree((2*start)+2,end)
      if(data.isEmpty) Some(Node(-99,left,right))
      else Some(Node(data.get,left,right))
    }

  }
val Root= Build_tree(0,len-1)
preorderTraversal(Root)
}
