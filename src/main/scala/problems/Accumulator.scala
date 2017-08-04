// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Count incoming trues
// (increase counter every clock if 'in' is asserted)
//
class Accumulator extends Module {
  val io = IO(new Bundle {
      //input is defined as UInt
      //can perform computation directly 
    val in  = Input(UInt(1.W)) 
    val out = Output(UInt(8.W))
  })

  // Implement below ----------
  io.out := 0.U
 
  val count = RegInit(0.U(8.W))

  when (io.in === true.B) {
     count := count + 1.U    
  }

     io.out := count
  
  //implementation given in solution, a bit better optimization
  //val count = RegInit(0.U(8.W))
  //count := count + io.in
  //io.out := count

  // Implement above ----------
}
