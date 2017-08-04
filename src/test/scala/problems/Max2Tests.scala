// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester

// Problem:
//
// Implement test with PeekPokeTester
//

// in trait PeekPokeTester
// it has
// def rnd: Scala.util.Random

// inside Scala.util.Random
// def nextInt(n: Int): Int

// Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) 
// and the specified value (exclusive), drawn from this random number generator's sequence.
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  for (i <- 0 until 10) {

    // Implement below ----------
    val in0 = rnd.nextInt(256)  //Max2 select 2 8-bit numbers
    val in1 = rnd.nextInt(256)

    poke(c.io.in0, in0)
    poke(c.io.in1, in1)
    step(1)
    expect(c.io.out, if (in0 > in1) in0 else in1)  //functional property in Scala

    // Implement above ----------
  }
}
