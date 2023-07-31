package boardGame;

public class Board {

	private int rows; // quantidade de linhas do tabuleiro (padr�o 8)
	private int columns; // Quantidade de colunas do tabuleiro (padr�o 8)
	private Piece[][] pieces; // Criando uma matriz de pe�as

	public Board(int rows, int columns) {

		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro na cria��o do tabuleiro: � necess�rio pelo menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // Cada posi��o da matriz vai ser um objeto pe�a (Piece)
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// Retornar uma pe�a atraves das coordenadas separadas
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posi��o inexistente no tabuleiro");
		}
		return pieces[row][column];
	}

	// Retornar uma pe�a atraves da posi��o
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posi��o inexistente no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// Metodo responsavel por colocar uma pe�a no tabuleiro
	public void PlacePiece(Piece piece, Position position) {

		if (thereIsAPiece(position)) {
			throw new BoardException("J� existe uma pe�a na posi��o " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; // Pegar a matriz na posi��o dada e atribuir a pe�a
		piece.position = position;
	}

	// Metodo auxiliar do de baixo
	private boolean positionExists(int row, int column) {
		// Quando uma posi��o existe no tabuleiro?
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// Metodo para ver se a posi��o o tabuleiro realmente existe
	// Tabuleiro � uma matriz 8x8, logo a posi��o (9,1) por exemplo, n�o existe
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	// Metodo para analisar se na posi��o especifica do tabuleiro existe j� uma pe�a
	public boolean thereIsAPiece(Position position) {

		// Testando se essa posi��o no tabuleiro existe
		if (!positionExists(position)) {
			throw new BoardException("Posi��o inexistente no tabuleiro");
		}

		// Como fa�o para testar se tem uma pe�a em tal posi��o
		return piece(position) != null;

	}

}
