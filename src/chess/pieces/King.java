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
		return "K"; // Vai aparecer o "K" na posi�ao do torre no tabuleiro
	}
	
	
	// M�todo para verificar se ele pode se mover para a posi��o especifica
	// No caso essa posi�ao tem que estar vazia ou ter um pe�a advers�ria
	// Diferente da torre que realizamos todas essas verifica��es diretamente em possibleMoves
	// Aqui definimos uma fun��o para isto
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position); // pegando a pe�a no tabuleiro na posi��o especificada
		return p == null || p.getColor() != getColor(); // se a posi��o == null ou seja n�o h� pe�a e se h� pe�a ela for inimiga (cor diferente) = True
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		// O rei pode andar uma casa em qualquer dire��o, vamos fazer a l�gica
				
		
		// Acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// Abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		// Direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}		
		
		// Esquerda
		p.setValues(position.getRow() , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		// Esquerda-Cima
		p.setValues(position.getRow() -1 , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		
		// Esquerda-baixo
		p.setValues(position.getRow() +1 , position.getColumn() - 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
		
		
		// direita-baixo
		p.setValues(position.getRow() +1, position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		// direita-cima
		p.setValues(position.getRow()-1 , position.getColumn() + 1);
		if (getBoard().positionExists(p)   && canMove(p) ) { // Enquanto a posi��o existir no tabuleiro e ele pode mover
			mat[p.getRow()][p.getColumn()] = true;
		}	
			
	
		
		
		
		return mat;
		
		
		
		
		
	}
	
	
	
	
	

}

