import org.junit.*;
import static org.junit.Assert.*;

public class QueensPuzzleTest {

  @Test
  public void test_getExampleSolution(){

    boolean[][] board = QueensPuzzle.getExampleSolution();

    assertNotNull("example board must not be null", board);
    assertEquals("example board must have exactly 8 rows", 8, board.length);

    for (int i = 0; i < 8; i++){
      assertNotNull("no row must be null", board[i]);
      assertEquals("all rows must have 8 entries", 8, board[i].length);
    }

    int[] queens = { 7, 3, 0, 2, 5, 1, 6, 4 };

    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){

        if (j == queens[i])
          assertTrue("Entry at " + i + "," + j + " must be true", board[i][j]);
        else
          assertFalse("Entry at " + i + "," + j + " must be false", board[i][j]);
      }

    }

  }

  @Test
  public void test_checkBoard(){

    boolean[][] board = null;

    final boolean[][] board0 = board;
    assertThrows("checkBoard must throw IllegalArgumentException on null",
        IllegalArgumentException.class, () -> QueensPuzzle.checkBoard(board0));

    board = new boolean[7][];

    final boolean[][] board1 = board;
    assertThrows("checkBoard must throw IllegalArgumentException on invalid number of rows",
        IllegalArgumentException.class, () -> QueensPuzzle.checkBoard(board1));

    board = new boolean[8][];

    for (int i = 0; i < 8; i++){
      final boolean[][] board2 = board;
      assertThrows("checkBoard must throw IllegalArgumentException if a row is null",
          IllegalArgumentException.class, () -> QueensPuzzle.checkBoard(board2));
      board[i] = new boolean[i];
      final boolean[][] board3 = board;
      assertThrows("checkBoard must throw IllegalArgumentException if a row has not eight columns",
          IllegalArgumentException.class, () -> QueensPuzzle.checkBoard(board3));

      board[i] = new boolean[8];
    }

    try {
      QueensPuzzle.checkBoard(board);
    } catch (Throwable e){
      fail("checkBoard must not throw an exception if the board is valid");
    }

  }

  @Test
  public void test_canCapture(){
    boolean board[][] = new boolean[8][8];

    for (int i = 0; i < 8; i++){
      for (int j = 0; j < 8; j++){
        assertFalse("There are no queens on the board => cannot capture anything", 
            QueensPuzzle.canCapture(board, i, j));
      }
    }

    board[3][3] = true;
    assertFalse("3,3 cannot capture anything", QueensPuzzle.canCapture(board,3,3));

    assertTrue("3,0 can capture 3,3", QueensPuzzle.canCapture(board,3,0));
    assertTrue("3,7 can capture 3,3", QueensPuzzle.canCapture(board,3,7));

    assertTrue("0,3 can capture 3,3", QueensPuzzle.canCapture(board,0,3));
    assertTrue("7,3 can capture 3,3", QueensPuzzle.canCapture(board,7,3));

    assertTrue("1,1 can capture 3,3", QueensPuzzle.canCapture(board,1,1));
    assertTrue("7,7 can capture 3,3", QueensPuzzle.canCapture(board,7,7));

    assertTrue("5,1 can capture 3,3", QueensPuzzle.canCapture(board,5,1));
    assertTrue("1,5 can capture 3,3", QueensPuzzle.canCapture(board,1,5));

    assertFalse("0,1 cannot capture 3,3", QueensPuzzle.canCapture(board,0,1));
    assertFalse("1,6 cannot capture 3,3", QueensPuzzle.canCapture(board,1,6));
    assertFalse("5,2 cannot capture 3,3", QueensPuzzle.canCapture(board,5,2));
    assertFalse("6,5 cannot capture 3,3", QueensPuzzle.canCapture(board,6,5));

  }

  @Test
  public void test_isSolution(){

    assertThrows("must throw IllegalArgumentException on null",
        IllegalArgumentException.class, () -> QueensPuzzle.isSolution(null));
    assertThrows("must throw IllegalArgumentException on invalid size",
        IllegalArgumentException.class, () -> QueensPuzzle.isSolution(new boolean[7][]));

    boolean board[][] = new boolean[8][8];

    int[] queens = { 7, 3, 0, 2, 5, 1, 6, 4 };

    for (int i = 0; i < 8; i++){

      // assertFalse("there are less than 8 queens on the board -> no solution",
      //     QueensPuzzle.isSolution(board));

      board[i][queens[i]] = true;
    }

    assertTrue("isSolution must return true on actual solution",
        QueensPuzzle.isSolution(board));

    board[4][2] = true;
    assertFalse("isSolution must return false if a queen can be captured",
        QueensPuzzle.isSolution(board));


  }
  
}
