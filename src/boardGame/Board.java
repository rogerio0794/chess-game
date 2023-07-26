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
	
	
	// Retornar uma pe�a atraves das coordenadas separadas
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
 	
	
	// Retornar uma pe�a atraves da posi��o
		public Piece piece(Position position) {
			return pieces[position.getRow()][position.getColumn()];
		}
		
		
	// Metodo responsavel por colocar uma pe�a no tabuleiro
	public void PlacePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece; // Pegar a matriz na posi��o dada e atribuir a pe�a
		piece.position = position;
	}
	
	
	
	
	
}
