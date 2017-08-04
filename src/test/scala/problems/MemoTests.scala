// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

class MemoTests(c: Memo) extends PeekPokeTester(c) {
  def rd(addr: Int, data: Int) = {
    poke(c.io.ren, 1)
    poke(c.io.rdAddr, addr)
    step(1)
    expect(c.io.rdData, data)
    poke(c.io.ren,0)
  }
  def wr(addr: Int, data: Int)  = {
    poke(c.io.wen,    1)
    poke(c.io.wrAddr, addr)
    poke(c.io.wrData, data)
    step(1)
    poke(c.io.wen,0)
  }
  // 
  // for testing simple dual port RAM, perform rd and wr at the same cycle
  def wrRd (wrAddr: Int, wrData: Int, rdAddr: Int, rdData: Int) = {
    poke(c.io.wen, 1)
    poke(c.io.wrAddr, wrAddr)
    poke(c.io.wrData, wrData)
    poke(c.io.ren, 1)
    poke(c.io.rdAddr, rdAddr)
    step(1)
    expect(c.io.rdData, rdData)
    poke(c.io.wen, 0)
    poke(c.io.ren, 0)
    
  }
  wr(0, 1)
  rd(0, 1)
  wr(0, 1)
  rd(0, 1)
  wr(5, 2)
  wrRd (9, 11, 5, 2)
 // wr(9, 11)
  rd(9, 11)
}
