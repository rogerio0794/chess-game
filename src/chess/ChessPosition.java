package chess;

import boardGame.Position;

public class ChessPosition {

	private char column; // Coluna é a,b,c,d,e,f,g e h
	private int row; // linha é 1 a 8

	public ChessPosition(char column, int row) {

		if (column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Erro na instanciação de ChessPosition. Valores validos são de a1 até h8");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	// Converter posição da matriz para posição do tabuleiro
	protected Position toPosition() {
		// Linha é 8 - linha do xadrex
		// a = 0 na matriz
		// b = 1 na matriz
		// c = 2 na matriz
//		'a' - 'a' = 0
//		'b' - 'a' = 1
//		'c' - 'a' = 2	
		// Logo: Coluna é coluna do xadrex - 'a'
		return new Position(8 - row, column - 'a');
	}

	// Converter da posição do tabuleiro para posição da matriz
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' + position.getColumn()), 8 - position.getRow());
	}

	@Override
	public String toString() {
		return "" + column + row;
	}
	
	
	

}
