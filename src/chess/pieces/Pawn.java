package chess.pieces;

import boardGame.Board;
import boardGame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	
	// Referencia (associação para partida)
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}   

	
	
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		
		Position p = new Position(0, 0);
		// O peao pode andar uma casa para frente
		// Caso seja a primeira jogada ele pode andar duas casas para frente
		// Peao branco: Se tiver uma peça adversária em cima-direita e cima-esquerda ele pode mover para essa direção e capturar e peça
		// Peao preto: Se tiver uma peça adversária em baixo-direita e baixo-esquerda ele pode mover para essa direção e capturar e peça
		
		
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
			
			
			// En passant
			// Preciso verificar se meu peao está na linha 5 do xadrez (3 da matriz), que a diagonal acima esteja livre e que ao meu lado tem um peão vulneravel
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() -1);
				if (getBoard().positionExists(left)  && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getRow(), position.getColumn() +1);
				if (getBoard().positionExists(rigth)  && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
				mat[rigth.getRow() - 1][rigth.getColumn()] = true;
				}
			}
			
	
			
			
			
			
			
			
			// Peças pretas
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
			
			
			// En passant
			// Preciso verificar se meu peao está na linha 5 do xadrez (3 da matriz), que a diagonal acima esteja livre e que ao meu lado tem um peão vulneravel
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() -1);
				if (getBoard().positionExists(left)  && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow()+1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getRow(), position.getColumn() +1);
				if (getBoard().positionExists(rigth)  && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
				mat[rigth.getRow()+1][rigth.getColumn()] = true;
				}
			}
			
			
			
		}
		
		

		
		
		
		
		
		
		
		return mat;
	}
	
	
	@Override
	public String toString() {
		return "P"; // Vai aparecer o "P" na posiçao do peao no tabuleiro
	}

}
