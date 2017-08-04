// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a loadable shift register with four 4-bit stages using Vec
// Shift occurs if 'shift' is asserted
// Load  occurs if 'load'  is asserted
// Whole state should be replaced with 'ins' when loaded
//
class VecShiftRegister extends Module {
  val io = IO(new Bundle {
    val ins   = Input(Vec(4, UInt(4.W)))
    val load  = Input(Bool())
    val shift = Input(Bool())
    val out   = Output(UInt(4.W))
  })
  // Implement below ----------

  val initValues = Seq.fill(4){0.U(4.W)}
  val delays = RegInit(Vec(initValues))

  io.out := delays(3)  //remind you to default the output
  
  when(io.shift){
     delays(3) := delays(2)
     delays(2) := delays(1)
     delays(1) := delays(0)
  }

  when(io.load){
     // delays := io.ins  //it works
     // alternatively, we can connect each line seperately
     delays <> io.ins   // bulk connection for Vec type
     // bulk connection is primarily used for connecting interfaces 
     // between module io and sub (user defined) module io 
  }


  // Implement above ----------
}
