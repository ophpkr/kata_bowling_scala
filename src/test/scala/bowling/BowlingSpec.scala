package bowling

import org.scalatest.{FunSpec, Matchers}

class BowlingSpec extends FunSpec with Matchers {

	val game = Game(Bowling(0, List()),0)



	describe("Bowling score") {

		it("should be 0 when all roll into gutter"){
			assert(BowlingOperations.playGame20(game, pinsDown=0) == 0)
		}

		it("should be 20 when all roll made 1 pin down"){
			assert(BowlingOperations.playGame20(game, pinsDown=1) == 20)
		}

    // I didn't achieve to implement the case of strikes correctly
		it("should be 300 when all roll made strike"){
      assert(BowlingOperations.playGame20(game, pinsDown=10) == 300)
		}
	}
}

object BowlingOperations {

	def playGame20(game: Game, value:Int = 0, pinsDown: Int): Int = {
		if (game.numRoll == 20) game.bowling.score
		else {
			val ng = game.play(pinsDown)
			playGame20(ng, value + 1, pinsDown)
		}
	}

}