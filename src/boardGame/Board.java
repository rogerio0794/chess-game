package boardGame;

public class Board {
	
	
	private int rows;   // quantidade de linhas do tabuleiro (padr�o 8)
	private int columns; // Quantidade de colunas do tabuleiro (padr�o 8)
	private Piece[][] pieces;  // Criando uma matriz de pe�as
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // Cada posi��o da matriz vai ser um objeto pe�a (Piece)
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	
	
	
	
	
	
	
	
}
