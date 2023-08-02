package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "K"; // Vai aparecer o "K" na posiçao do torre no tabuleiro
	}
	
	
	// Método para verificar se ele pode se mover para a posição especifica
	// No caso essa posiçao tem que estar vazia ou ter um peça adversária
	// Diferente da torre que realizamos todas essas verificações diretamente em possibleMoves
	// Aqui definimos uma função para isto
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // pegando a peça no tabuleiro na posição especificada
		return p == null || p.getColor() != getColor(); // se a posição == null ou seja não há peça e se há peça ela for inimiga (cor diferente) = True
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		// O rei pode andar uma casa em qualquer direção, vamos fazer a lógica
				
		
		// Acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		// Direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}		
		
		// Esquerda
		p.setValues(position.getRow() , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		// Esquerda-Cima
		p.setValues(position.getRow() -1 , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		
		// Esquerda-baixo
		p.setValues(position.getRow() +1 , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		// direita-baixo
		p.setValues(position.getRow() +1, position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// direita-cima
		p.setValues(position.getRow()-1 , position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posição existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
			
	
		
		
		
		return mat;
		
		
		
		
		
	}
	
	
	
	
	

}

