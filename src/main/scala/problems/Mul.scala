// See LICENSE.txt for license details.
package problems

import chisel3._
import chisel3.util._
import scala.collection.mutable.ArrayBuffer

// Problem:
//
// Implement a four-by-four multiplier using a look-up table.
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })
  val mulsValues = new ArrayBuffer[UInt]()

  // Calculate io.z = io.x * io.y by generating a table of values for mulsValues

  // Implement below ----------
  for (i <- 0 until 16)
    for (j <- 0 until 16)
      mulsValues += (i * j).asUInt(8.W) //Scala ArrayBuffer with incrementally added elements (using +=)
  val tbl = Vec(mulsValues)
  //val rom = tbl(counter(256.U)) //generating address????

  //question: how to generate address for ROM????
  io.z := tbl(Cat(io.x, io.y))

  // Implement above ----------
}
