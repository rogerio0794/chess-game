package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R"; // Vai aparecer o "R" na posiçao do torre no tabuleiro
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		
		// Acima
		p.setValues(position.getRow() - 1, position.getColumn()); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() -1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn()); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
	
		
        // Direita
		p.setValues(position.getRow(), position.getColumn() + 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Esquerda
		p.setValues(position.getRow(), position.getColumn() - 1); // Analisando a posição acima da peça
		while (getBoard().positionExists(p)   && !getBoard().thereIsAPiece(p) ) { // Enquanto a posição existir no tabuleiro e não existir peça na posição
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}				
		if (getBoard().positionExists(p)   && isThereOpponentPiece(p)) { // Peça inimiga na posição?
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		
		
		
		
		return mat;
	}

}

