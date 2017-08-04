// See LICENSE.txt for license details.
package problems

import chisel3._
//import chisel3.util.{Valid, DeqIO}
import chisel3.util._

// Problem:
// Implement a GCD circuit (the greatest common divisor of two numbers).
// Input numbers are bundled as 'RealGCDInput' and communicated using the Decoupled handshake protocol
//
class RealGCDInput extends Bundle {
  val a = UInt(16.W)
  val b = UInt(16.W)        //Input??
}

class RealGCD extends Module {
  val io  = IO(new Bundle {
    val in  = DeqIO(new RealGCDInput())
    val out = Output(Valid(UInt(16.W)))
  })

  val sCheck :: sInitialize :: sProcess :: sIsDone :: sOutput :: Nil = Enum(5)
  val state = RegInit(sCheck)
  val big = Reg(UInt(16.W))
  val small = Reg(UInt(16.W))
 
  // defaults
  io.out.valid := false.B
  io.out.bits := 0.U 

  switch(state){
  	is (sCheck){
  		when(io.in.valid) {state := sInitialize}
  	}
  	is (sInitialize){
  	    small := io.in.bits.a
  	    big := io.in.bits.b
  	    io.in.ready := true.B
  	    state := sProcess
  	}
  	is (sProcess){
        state := sIsDone
        when(big < small){
  			   big := small
  		       small := big
  		}.otherwise{big := big - small}
  		
  	}
  	is (sIsDone){
  		when(big === 0.U) {state := sOutput} // this makes trouble
  		.otherwise{state := sProcess}
  	//	printf("hi")
  	}
 
  	is (sOutput){
  		io.out.bits := small
    	io.out.valid := true.B
     	io.in.ready := false.B
     	state := sCheck
  	}
 
  }

/*
  val x = Reg(UInt())
  val y = Reg(UInt())
  val p = RegInit(false.B)

  io.in.ready := !p

  when (io.in.valid && !p) {
    x := io.in.bits.a
    y := io.in.bits.b
    p := true.B
  } 

  when (p) {
    when (x > y)  { x := y; y := x } 
    .otherwise    { y := y - x }
  }

  io.out.bits  := x
  io.out.valid := y === 0.U && p
  when (io.out.valid) {
    p := false.B
  }
*/
  // Implement below ----------
  // first implementation
 /*
  when(io.in.valid){
  	val big = RegInit(io.in.bits.a)
  	val small = RegInit(io.in.bits.b)

  	when (big < small){
  		//val temp = big
  		big := small
  		small := big
  	}.otherwise{
  		big := big - small   // when do modulus, gives error of uninfered width
  	}

    when (big === 0.U){
    	io.out.bits := small
    	io.out.valid := true.B
    	io.in.ready := true.B
    }

  }
 */
  // Implement above ----------

}
