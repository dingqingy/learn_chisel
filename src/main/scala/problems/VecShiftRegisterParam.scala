// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a parametrized simple shift register.
// 'n' is the number of elements in the shift register.
// 'w' is the width of one element.

class VecShiftRegisterParam(val n: Int, val w: Int) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(w.W))
    val out = Output(UInt(w.W))
  })

  // Implement below ----------
  val initValues = Seq.fill(n){0.U(w.W)}  //when to use U and UInt
  val delays = RegInit(Vec(initValues))	
  
  io.out := delays(n-1)
  delays(0) := io.in 

  // var i = 0; why??
  // for loop here is very similar to what happen in synthesizable verilog
  // will be unroll for synthesis
  for (i <- 1 until n){       
  	delays(i) := delays(i-1)
  }


}
// Implement above ----------
