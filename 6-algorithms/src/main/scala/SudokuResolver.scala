import scala.util.control.Breaks._

object SudokuResolver {
  type Sudoku = Array[Array[Int]]
  private type Location = (Int, Int)
  def resolve(sudoku: Sudoku): Option[Sudoku] = {
    def loop(x: Int, y: Int, sudoku: Sudoku): Option[Sudoku] = {

      (x, y) match {
        case (i, j) if i == 9 && j == 0 => Some(sudoku)
        case (i, j) if sudoku(i)(j) != 0 =>
          if (j == 8) loop(i + 1, 0, sudoku)
          else loop(i, j + 1, sudoku)
        case (i, j) =>
          var find: Boolean = false
          breakable {
            for (n <- 1 to 9) {
              sudoku(i)(j) = n
              if (
                isValidSudoku(sudoku) &&
                (if (j < 8) loop(i, j + 1, sudoku)
                 else loop(i + 1, 0, sudoku)).isDefined
              ) {
                find = true
                break
              }
            }
          }
          if (find) {
            Some(sudoku)
          } else {
            sudoku(i)(j) = 0
            None
          }
      }
    }
    loop(0, 0, sudoku)
  }

  private def isValidSudoku(sudoku: Sudoku): Boolean = {
    !(0 until 9).exists { i =>
      val row = (0 until 9).map(sudoku(i)(_)).filter(_ != 0)
      val col = (0 until 9).map(sudoku(_)(i)).filter(_ != 0)
      val square =
        (0 until 9).map(j => sudoku(j / 3 + (i / 3) * 3)(j % 3 + (i % 3) * 3)).filter(_ != 0)
      row.distinct.length != row.length ||
      col.distinct.length != col.length ||
      square.distinct.length != square.length
    }
  }
}
