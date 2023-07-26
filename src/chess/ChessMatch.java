package chess;

import boardGame.Board;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	
	private Board board; // Uma partida de xadrex precisa de uma tabuleiro
	
	
	public ChessMatch() {
		board = new Board(8, 8);  // Criando o tabuleiro 8x8 do xadrez
		initialSetup();
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
	
	
	// Iniciar a partida de xadreex colocando as pe�as no tabuleiro
	private void initialSetup() {
		board.PlacePiece(new Rook(board, Color.WHITE), new Position(4,1));
		board.PlacePiece(new King(board, Color.WHITE), new Position(5,1));
		board.PlacePiece(new Rook(board, Color.BLACK), new Position(6,4));
		board.PlacePiece(new Rook(board, Color.BLACK), new Position(0,0));
		
		
		// Perceba que as posi��es s�o correspondentes a matriz e n�o o tabuleiro posio��o 8a do tabuleiro corresponde a 0,0 da matriz
	}
	
	
	
	
	

}
