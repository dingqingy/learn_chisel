// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._

// Problem:
//
// Create a composition (chain) of two filters:
//
// SingleFilter - indicates that input is single decimal digit
// (i.e. is less or equal to 9)
//
// EvenFilter   - indicates that input is even number
//
abstract class Filter[T <: Data](dtype: T) extends Module {
  val io = IO(new Bundle {
    val in = Input(Valid(dtype))
    val out = Output(Valid(dtype))
  })
}

//learn more about high order function in scala
class PredicateFilter[T <: Data](dtype: T, f: T => Bool) extends Filter(dtype) {
  io.out.valid := io.in.valid && f(io.in.bits)
  io.out.bits  := io.in.bits
}

object SingleFilter {
  def apply[T <: UInt](dtype: T) = 
    //pass an anonymous function         T        =>    Bool
    Module(new PredicateFilter(dtype, (baobao: T) => baobao < 10.U)) 
    //try to do partial application, this fails
    //Module(new PredicateFilter(dtype, _ < 10.U)) 
    //investigate more on this
    
  //this is really bad: redundant, just like map a to a
  //def isDecimal[T <: UInt](dtype: T) = if (dtype < 10.U) true.B else false.B  
  
  //this is good
  //def isDecimal[T <: UInt](dtype: T): Bool = dtype < 10.U
}

object EvenFilter {
  def apply[T <: UInt](dtype: T) = 
    // Change function argument of Predicate filter below ----------
    // Module(new PredicateFilter(dtype, (dtype: T) % 2.U === 1.U => false.B))
    Module(new PredicateFilter(dtype, isEven))
    // Change function argument of Predicate filter above ----------
  //def isEven[T <: UInt](dtype: T) = if (dtype(0) === 0.U) true.B else false.B
  def isEven[T <: UInt](dtype: T): Bool = dtype(0) === 0.U
}

class SingleEvenFilter[T <: UInt](dtype: T) extends Filter(dtype) {
  // Implement composition below ----------
  val single = SingleFilter(dtype)
  val even = SingleFilter(dtype)
  io.in <> single.io.in
  single.io.out <> even.io.in
  even.io.out <> io.out
 

  // Implement composition above ----------
}
