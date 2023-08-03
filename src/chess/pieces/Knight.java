package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // pegando a peça no tabuleiro na posição especificada
		return p == null || p.getColor() != getColor(); // se a posição == null ou seja não há peça e se há peça ela for inimiga (cor diferente) = True
	}

	@Override
	public boolean[][] possibleMoves() {

		// Cavalo se movimento em L (8 movimentos possiveis)
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
				
		
		// Acima-esquerda
		p.setValues(position.getRow() - 2, position.getColumn() -1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Acima-direita
		p.setValues(position.getRow() - 2, position.getColumn() +1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Acima- esquerda2
		p.setValues(position.getRow() - 1, position.getColumn() -2);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Acima- direita2
		p.setValues(position.getRow() - 1, position.getColumn() +2);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		// Baixo-esquerda
		p.setValues(position.getRow() + 2, position.getColumn() -1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Baixo-direita
		p.setValues(position.getRow() + 2, position.getColumn() +1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Baixo- esquerda2
		p.setValues(position.getRow() + 1, position.getColumn() -2);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Baixo- direita2
		p.setValues(position.getRow() + 1, position.getColumn() +2);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}

		
		return mat;
	}
	
	@Override
	public String toString() {
		return "N"; // Vai aparecer o "N" na posiçao do cavalo no tabuleiro
	}

}
