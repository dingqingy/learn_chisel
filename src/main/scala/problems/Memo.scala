// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// Implement a dual port memory of 256 8-bit words.
// When 'wen' is asserted, write 'wrData' to memory at 'wrAddr'
// When 'ren' is asserted, 'rdData' holds the output
// of reading the memory at 'rdAddr'
//
//question: can we make a parametrized memory???
class Memo extends Module {
  val io = IO(new Bundle {
    val wen     = Input(Bool())
    val wrAddr  = Input(UInt(8.W))
    val wrData  = Input(UInt(8.W))
    val ren     = Input(Bool())
    val rdAddr  = Input(UInt(8.W))
    val rdData  = Output(UInt(8.W))
  })

  val mem = SyncReadMem(256, UInt(8.W))

  // Implement below ----------
  // simple dual port memory, can perform write and read at the same cycle
  // Note that
/** Read-after-write behavior 
  * (when a read and write to the same address are requested on the same cycle)
  * is undefined.
  * @note when multiple conflicting writes are performed on a Mem element, the
  * result is undefined (unlike Vec, where the last assignment wins)
  */

   when (io.wen) {mem.write(io.wrAddr,io.wrData)}
   io.rdData := mem.read(io.rdAddr,io.ren)

// single port  SRAM
// read and write conditions are mutually exclusive

//  when (io.wen) {mem.write(io.wrAddr,io.wrData)}
//  .otherwise{ io.rdData := mem.read(io.rdAddr,io.ren)}

  // Implement above ----------

}
