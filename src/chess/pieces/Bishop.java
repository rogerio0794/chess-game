package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	public Bishop(Board board, Color color) {
		super(board, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		
		// Bispo é diagonal, preciso de um while semelhante a torre
		
		
		// Diagonal cima-esquerda
		p.setValues(position.getRow() - 1, position.getColumn() - 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal cima-direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal baixo-esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Diagonal baixo-direita
		p.setValues(position.getRow() + 1, position.getColumn() + 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		
		
		
		
		
		return mat;
	}
	
	
	
	
	@Override
	public String toString() {
		return "B"; // Vai aparecer o "B" na posiçao do Bispo no tabuleiro
	}

}
