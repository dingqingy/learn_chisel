// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester
import math._

// Problem:
//
// Implement test for MaxN using PeekPokeTester
//
// my implementaton
class MaxNTests(c: MaxN) extends PeekPokeTester(c) {
  val maxRnd = pow(2, c.w).toInt
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      // Implement below ---------- 
      var ins = rnd.nextInt(maxRnd)
      poke(c.io.ins(i), ins)
      mx = if(ins > mx) ins else mx
      // Implement above ----------
    }
    step(1)
    // Implement below ----------
    expect(c.io.out, mx)
    // Implement above ----------
  }
}

// solution implemetation
/*
class MaxNTests(c: MaxN) extends PeekPokeTester(c) {
  val ins = Array.fill(c.n){ 0 }
  for (i <- 0 until 10) {
    var mx = 0
    for (i <- 0 until c.n) {
      ins(i) = rnd.nextInt(1 << c.w) 
      poke(c.io.ins(i), ins(i))
      mx = if (ins(i) > mx) ins(i) else mx
    }
    step(1)
    expect(c.io.out, mx)
  }
}
*/