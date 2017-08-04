// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util.Cat


// Problem:
//
// Implement a 16-bit Fibonacci Linear-feedback shift register
// with polynomial x^16 + x^14 + x^13 + x^11 + 1
// State change is allowed only when 'inc' is asserted
//
class LFSR16 extends Module {
  val io = IO(new Bundle {
    val inc = Input(Bool())
    val out = Output(UInt(16.W))
  })

  // Implement below ----------

  io.out := 0.U
  val lfsr = RegInit(1.U(16.W))  //initial seeds not given in the problem
  when (io.inc) {
      //simplest implementation
      //lfsr := Cat(lfsr(0) ^ lfsr(2) ^ lfsr(3) ^ lfsr(5) , lfsr(15,1))          
      
      //something like this gives error
      //lfsr(15) := lfsr(0) ^ lfsr(2) ^ lfsr(3) ^ lfsr(5)
      //lfsr(14,0) := lfsr(15,1)
      
      //something like this gives error
      //lfsr := Cat(lfsr(0) ^ lfsr(2) ^ lfsr(3) ^ lfsr(5) , lfsr >> 1.U)
      //val shift = lfsr >> 1.U
      
      //named wires for fan-out
      //val shift = lfsr(15,1)
      //val high = lfsr(0) ^ lfsr(2) ^ lfsr(3) ^ lfsr(5)
      //lfsr := Cat(high,shift)

      //functional abstraction
      def xor (a: UInt, b: UInt, c: UInt, d: UInt): UInt = a ^ b ^ c ^ d
      lfsr := Cat(xor(lfsr(0),lfsr(2),lfsr(3),lfsr(5)), lfsr(15,1))

  }

  io.out := lfsr
  // Implement above ----------
}
