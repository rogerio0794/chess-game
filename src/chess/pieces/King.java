package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	
	private ChessMatch chessMatch; // Associação!
	

	public King(Board board, Color color,ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}
	
	@Override
	public String toString() {
		return "K"; // Vai aparecer o "K" na posiçao do torre no tabuleiro
	}
	
	// Testar se a torre está apta para o ROQUE
	private boolean testRookCastrling (Position position) {
		
		// Pegando a peça na posição do rook
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		
		// Testando se tea a peça na posição, se é uma torre, se é amiga (cor igual) e não se moveu ainda
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
		
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
			
	
		
		// Movimento Roque, ver se a horizontal está vazia, se o rei não se moveu e nem está em cheque
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			
			
			// direita (Roque pequeno) pegando a posição da torre da direita em relação ao rei
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastrling(posT1)) {				
				
				// Testando se as casas estão vazias
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {					
					// Roque pequeno permitido
					mat[position.getRow()][position.getColumn() +2] = true;
				}
			}
			
			
		// esquerda (Roque grande) pegando a posição da torre da esquerda em relação ao rei
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastrling(posT2)) {				
				
				// Testando se as casas estão vazias
				Position p1 = new Position(position.getRow(), position.getColumn()  -1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {					
					// Roque grande permitido
					mat[position.getRow()][position.getColumn() -2] = true;
				}
			}
			
		}
		
		
		
		return mat;
		
		
		
		
		
	}
	
	
	
	
	

}

