package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
	}

	
	
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		// O peao pode andar uma casa para frente
		// Caso seja a primeira jogada ele pode andar duas casas para frente
		// Peao branco: Se tiver uma pe�a advers�ria em cima-direita e cima-esquerda ele pode mover para essa dire��o e capturar e pe�a
		// Peao preto: Se tiver uma pe�a advers�ria em baixo-direita e baixo-esquerda ele pode mover para essa dire��o e capturar e pe�a
		
		
		// Peao branco
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// Se for primeira jogada, pode-se mover duas casas
			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1,  position.getColumn());
			if (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)   && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			// Testando casas diagonais
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)   ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)   ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// Pe�as pretas
		} else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// Se for primeira jogada, pode-se mover duas casas
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1,  position.getColumn());
			if (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2)   && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			// Testando casas diagonais
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)   ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)   ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			
			
		}
		
		

		
		
		
		
		
		
		
		return mat;
	}
	
	
	@Override
	public String toString() {
		return "P"; // Vai aparecer o "P" na posi�ao do peao no tabuleiro
	}

}
