package chess;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;


// ChessPiece ainda � muito gen�rica, eu n�o tenho como definir os movimentos das pe�as aqui
//Logo usa-se abstact
public abstract class ChessPiece extends Piece {
	
	
	private Color color;
	private int moveCount; // Contagem de movimentos da pe�a

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
	
	public void increaseMovecount () {
		moveCount++;
	}
	
	
	public void decreaseMovecount () {
		moveCount--;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	
	// Pegar a posi��o da pe�a do xadrez
	public ChessPosition getChessPosition() {			
		return ChessPosition.fromPosition(position);
		
	}
	
	
	// Ver se tem uma pe�a inimiga na posi��o especifica
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);  // getBoard � um metodo da classe Piece
		return p != null &&  p.getColor()  != color ;
		
	}
	
	

}
