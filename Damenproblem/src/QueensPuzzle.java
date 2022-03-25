
public class QueensPuzzle {

	public static void main(String[] args) {
		QueensPuzzle.printBoard(getExampleSolution());
		

	}
	public static boolean[][] getExampleSolution(){
			return new boolean[][] {
					             {false,false,false,false,false,false,false,true},
					             {false,false,false,true,false,false,false,false},
			                     {true,false,false,false,false,false,false,false},
			                     {false,false,true,false,false,false,false,false},
			                     {false,false,false,false,false,true,false,false},
			                     {false,true,false,false,false,false,false,false},
			                     {false,false,false,false,false,false,true,false},
			                     {false,false,false,false,true,false,false,false}};
		
	}

public static void printBoard(boolean[][] board) {
    System.out.println("+"+"-"+ "+"+"-"+"+"+"-"+"+"+"-"+"+"+"-"+"+"+"-"+"+"+ "-"+ "+"+ "-"+ "+");
	
	for (int i = 0; i < board.length; i++) {
            for (int y = 0; y < board.length; y++) {
				if (board[i][y] == true) {    //wenn true also queen gefunden worden ist dann q setzen
					System.out.print("Q|");
				}else if (board[i][y]==false){  //wenn false dann leer
					System.out.println(" |");
					
				}if(y==board.length-1) {
					System.out.println();
				}
			}
		}
		System.out.printf("+"+"-"+ "+"+"-"+"+"+"-"+"+"+"-"+"+"+"-"+"+"+"-"+"+"+ "-"+ "+"+ "-"+ "+");
	}


public static void checkBoard(boolean[][] board) {
		
		if (board == null) {
			throw new IllegalArgumentException("Darf nicht null sein");  //check ob null ist
		}
		for (int i = 0; i < board.length; i++) {
			 if (board[i] == null || board[i].length!=8) { //durch values itterieren um zu check ob davon eine null gibt bzw ob eine ziele wenigere8 gibt
				throw new IllegalArgumentException("array rows sind nicht null ");
			}
		} 

	}

public static boolean canCapture(boolean[][]board,int row, int col) {

	if (board[row][col] == true) // setzen von der postion der queen
			return false;
    
		for (int i = 0; i < board.length; i++) { // check rows
			for (int y = 0; y < board[i].length; y++) { // check colums
				if (board[i][y] == true) { // durch die ziele gehen und jeweil erhöhung der spalte
					return true;
				}

			}
		}
        
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) { // check oben diagonal links
			if (board[i][j] == true)
				return true;
		}
		for (int i = row, j = col; j >= 0 && i < board.length; i++, j--) { // check unten diagonal link//	
			if (board[i][j] == true) {
				return true;
			}
				}
		return false;
}


	
public static boolean isSolution(boolean[][]board) {
		checkBoard(board);
		

	int anzahlDamen=0;
	for (int i=0;i<board.length;i++) {
		for (int y=0;y<board[i].length;y++){
			if(board[i][y]==true) { 
				anzahlDamen+=1;
			}
		}
	}
   if (anzahlDamen!=8){
	   return false;
   }
return true;

}


	}
	

