// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a shift register with four 8-bit stages.
// Shift should occur on every clock.
//
class VecShiftRegisterSimple extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(8.W))
    val out = Output(UInt(8.W))
  })

  val initValues = Seq.fill(4) { 0.U(8.W) }   //Seq is a collection that containing a lot sub-class, more on scala collections
  val delays = RegInit(Vec(initValues))

  // Implement below ----------

  io.out := delays(3)
  delays(3) := delays(2)
  delays(2) := delays(1)
  delays(1) := delays(0)
  delays(0) := io.in 

  // Implement above` ----------
}
