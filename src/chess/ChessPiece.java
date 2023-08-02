package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;


// ChessPiece ainda é muito genérica, eu não tenho como definir os movimentos das peças aqui
//Logo usa-se abstact
public abstract class ChessPiece extends Piece {
	
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	// Pegar a posição da peça do xadrez
	public ChessPosition getChessPosition() {			
		return ChessPosition.fromPosition(position);
		
	}
	
	
	// Ver se tem uma peça inimiga na posição especifica
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);  // getBoard é um metodo da classe Piece
		return p != null &&  p.getColor()  != color ;
		
	}
	
	

}
