package bowling

case class Frame(_firstRoll: Int, _secondRoll: Int) {

  def firstRoll = _firstRoll
  def secondRoll = _secondRoll

  def rollInto(numTry: Int, numberOfPin: Int): Frame = {
    if (numTry == 1) {
      this.copy(_firstRoll = numberOfPin)
    }
    else {
      this.copy(_secondRoll = numberOfPin)
    }
  }

  def makeStrike(): Frame = {
    this.copy(_firstRoll = 10, _secondRoll = 0)
  }
}

case class Bowling(_score: Int, _frames: List[Frame]){

  def score = _score
  def frames = _frames

  def updateScore(newScore: Int): Bowling = {
    this.copy(_score = newScore)
  }

  def addFrame(frame: Frame): Bowling = {
    val newFrames = this.frames :+ frame
    this.copy(_frames = newFrames)
  }

  def updateLastFrameRoll(pinsDown: Int): Bowling = {
    val uFrame = this.frames.last.rollInto(2, pinsDown)
    val nFrames = this.frames.take(this.frames.size - 1)
    this.copy(_frames = nFrames).addFrame(uFrame)
  }
}

case class Game(_bowling: Bowling, _numRoll: Int) {

  def bowling = _bowling
  def numRoll = _numRoll

  def play(pinsDown: Int):Game = {
    println(this.numRoll)
    if (this.numRoll % 2 == 0) {
      val frame = Frame(0,0)

      if (pinsDown == 10) {
        println("a")
        val nframe = frame.makeStrike
        val ngame = this.copy(_bowling = this.bowling.addFrame(nframe))
        val nscore = calcScore(ngame, 1)
        println("score strike " + nscore)
        ngame.copy(_bowling = ngame.bowling.updateScore(nscore), _numRoll = numRoll + 2)
      }
      else {
        println("b")
        val nframe = frame.rollInto(1, pinsDown)
        val nbowl = this.bowling.addFrame(nframe)
        val ngame = this.copy(_bowling = nbowl)
        val nscore = calcScore(ngame, 1)
        println("score 1er roll " + nscore)
        val newgame = ngame.copy(_bowling = ngame.bowling.updateScore(nscore), _numRoll = numRoll + 1)
        newgame
      }
    }
    else {
      println("c")
      val nbowl = this.bowling.updateLastFrameRoll(pinsDown)
      val ngame = this.copy(_bowling = nbowl)
      val newScore = calcScore(ngame, 2)
      println("score 2eme roll " + newScore)
      val newbowl = nbowl.updateScore(newScore)
      ngame.copy(_bowling = newbowl, _numRoll = numRoll + 1)
    }
  }

  def calcScore(game: Game, rollNum: Int): Int = {
    val beforeLastFrame = game.bowling.frames.size match {
      case 0 => {
        Frame(0, 0)
      }
      case 1 => {
        Frame(0, 0)
      }
      case _ => {
        game.bowling.frames.take(game.bowling.frames.size - 1).last
      }
    }
    beforeLastFrame.firstRoll match {
      case 10 => {
        if (game.bowling.frames.size > 2 && game.bowling.frames.take(game.bowling.frames.size - 2).last.firstRoll == 10){
          game.bowling.score + 10 + game.bowling.frames.last.firstRoll + game.bowling.frames.last.secondRoll
        }
        else {
          game.bowling.score + game.bowling.frames.last.firstRoll + game.bowling.frames.last.secondRoll
        }


      }
      case _ => {
        if (beforeLastFrame.secondRoll + beforeLastFrame.firstRoll == 10) {
          game.bowling.score + 10 + game.bowling.frames.last.firstRoll
        }
        else {
          if (game.bowling.frames.last.firstRoll == 10 || (game.bowling.frames.last.firstRoll + game.bowling.frames.last.secondRoll == 10)) {
            game.bowling.score
          }
          else {
            println("ok")
              if (rollNum == 1){
                game.bowling.score + game.bowling.frames.last.firstRoll
              }
              else {
                game.bowling.score + game.bowling.frames.last.secondRoll
              }
          }
        }
      }
    }


  }

}

