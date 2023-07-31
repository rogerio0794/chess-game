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
	
	// Iniciar a partida de xadreex colocando as pe�as no tabuleiro pela posi��o da pe�a no tabuleiro
		private void placenewPieace(char column, int row, ChessPiece piece) {
			board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
		}
	
	
	// Iniciar a partida de xadreex colocando as pe�as no tabuleiro pela posi��o da pe�a na matriz
	private void initialSetup() {			
		// Aqui colocamos a pe�a de acordo com as posi��es to tabuleiro de xadrez e n�o da matriz
		placenewPieace('b',6, new Rook(board, Color.WHITE));
		placenewPieace('a',8, new King(board, Color.WHITE));
		placenewPieace('c',1, new Rook(board, Color.BLACK));
		placenewPieace('e',3, new King(board, Color.BLACK));
		
		
//		board.PlacePiece(new Rook(board, Color.WHITE), new Position(4,1));
//		board.PlacePiece(new King(board, Color.WHITE), new Position(5,1));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(6,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(0,0));
		
		
		// Ver os erros
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(6,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(9,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(9,9));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(0,9));
	
		
		// Perceba que as posi��es s�o correspondentes a matriz e n�o o tabuleiro posio��o 8a do tabuleiro corresponde a 0,0 da matriz
	}
	
	
	
	
	

}
