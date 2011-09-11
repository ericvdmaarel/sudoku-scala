package sudoku

import java.io.File

object Main {
  def main(args: Array[String]): Unit = {

    args match {
      case Array(f) => {
        val p = SudokuIO.readFile(new File(f))
        val sol = SudokuSolver.solve(p)
        SudokuIO.writeFile(System.out, sol)
      }
      case Array(f, a) => {
	val v = SudokuIO.readBatchFile(new File(f))
	val p = if (a=="p") v.par else v
	val s = p.map(SudokuSolver.solve(_))
	s.foreach(SudokuIO.writeFile(System.out, _))
      }
      case _ => println("Usage SudokuSolver <filename>")
    }
  }
}
