// See LICENSE.txt for license details.
package problems

import chisel3._

// Example:
//
// This is example of multiplexer 2-to-1 with 'sel' as control signal
// Multiplexed inputs are 'in0' and 'in1'
//
class Mux2 extends Module {
  val io = IO(new Bundle {
    val sel = Input(UInt(1.W))
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val out = Output(UInt(1.W))
  })
  io.out := (io.sel & io.in1) | (~io.sel & io.in0)
}

// Functional Module creation
// Alternatively, we can create a constructor than takes mux inputs as parameters 
// and returns the mux output
object Mux2 {
  def apply (sel: UInt, in0: UInt, in1: UInt): UInt = {
    val m = Module(new Mux2())
    m.io.in0 := in0
    m.io.in1 := in1
    m.io.sel := sel
    m.io.out
  }
}


// Problem:
//
// Build a 4-to-1 multiplexer out of three 2-to-1 multiplexers
// The first multiplexer is already done for you
//
class Mux4 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val in2 = Input(UInt(1.W))
    val in3 = Input(UInt(1.W))
    val sel = Input(UInt(2.W))
    val out = Output(UInt(1.W))
  })
  //the first implementation: create instance of Mux Class, and then wire them together 
  /*
  val m0 = Module(new Mux2())
  m0.io.sel := io.sel(0)
  m0.io.in0 := io.in0
  m0.io.in1 := io.in1

  //Implement below ----------
  val m1 = Module(new Mux2())
  m1.io.sel := io.sel(0)
  m1.io.in0 := io.in2
  m1.io.in1 := io.in3

  val m2 = Module(new Mux2())
  m2.io.sel := io.sel(1)
  m2.io.in0 := m0.io.out
  m2.io.in1 := m1.io.out

  io.out := m2.io.out
  //Implement above ----------
  */

  io.out := 
    Mux2(
      io.sel(1),
      Mux2(io.sel(0), io.in0, io.in1),
      Mux2(io.sel(0), io.in2, io.in3))
}
