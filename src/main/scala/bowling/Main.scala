import bowling.{Bowling, Game}

object MainGame {

  def main(args: Array[String]): Unit = {

    val game = Game(Bowling(0, List()), 0)
    val g0 = game.play(1)
    val g1 = g0.play(1)
    val g2 = g1.play(1)
    val g3 = g2.play(1)
    val g4 = g3.play(1)
    val g5 = g4.play(1)
    val g6 = g5.play(1)
    val g7 = g6.play(1)
    val g8 = g7.play(1)
    val g9 = g8.play(1)
    val g10 = g9.play(1)
    val g11 = g10.play(1)
    val g12 = g11.play(1)
    g12.play(1)
    println("coucou")
  }
}