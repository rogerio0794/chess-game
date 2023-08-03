package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		
		// Mistura de torre e bispo
		// Pode se mover quantas casas quiser na diagonal e na horizonal e vertical
		// Colocar as fun��es de movimento da torre e bispo
		
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		
		
		// Torre
		// Acima
		p.setValues(position.getRow() - 1, position.getColumn()); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() -1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
	
		
        // Direita
		p.setValues(position.getRow(), position.getColumn() + 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		// Bispo
		// Diagonal cima-esquerda
		p.setValues(position.getRow() - 1, position.getColumn() - 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal cima-direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal baixo-esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal baixo-direita
		p.setValues(position.getRow() + 1, position.getColumn() + 1); // Analisando a posi��o acima da pe�a
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posi��o existir no tabuleiro e n�o existir pe�a na posi��o
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Pe�a inimiga na posi��o?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		return mat;
		
		
		
	}
	
	@Override
	public String toString() {
		return "Q"; // Vai aparecer o "Q" na posi�ao da rainha no tabuleiro
	}
	

}
