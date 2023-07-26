package chess;

import boardGame.Board;

public class ChessMatch {
	
	private Board board; // Uma partida de xadrex precisa de uma tabuleiro
	
	
	public ChessMatch() {
		board = new Board(8, 8);  // Criando o tabuleiro 8x8 do xadrez
	}
	
	
	// Metodo para retornar a pe�a de xadrex dentro de uma posi��o especifica
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0;  i<board.getRows(); i++) {
			for (int j = 0;  j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i,j); // Downcasting para compilador entender que n�o � do tipo piece e sim ChessPiece 
				
			}
		}
		return mat;
		
	}
	
	
	
	
	

}
