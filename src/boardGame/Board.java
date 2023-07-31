package boardGame;

public class Board {

	private int rows; // quantidade de linhas do tabuleiro (padrão 8)
	private int columns; // Quantidade de colunas do tabuleiro (padrão 8)
	private Piece[][] pieces; // Criando uma matriz de peças

	public Board(int rows, int columns) {

		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro na criação do tabuleiro: é necessário pelo menos 1 linha e 1 coluna");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // Cada posição da matriz vai ser um objeto peça (Piece)
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// Retornar uma peça atraves das coordenadas separadas
	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição inexistente no tabuleiro");
		}
		return pieces[row][column];
	}

	// Retornar uma peça atraves da posição
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição inexistente no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// Metodo responsavel por colocar uma peça no tabuleiro
	public void PlacePiece(Piece piece, Position position) {

		if (thereIsAPiece(position)) {
			throw new BoardException("Já existe uma peça na posição " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; // Pegar a matriz na posição dada e atribuir a peça
		piece.position = position;
	}

	// Metodo auxiliar do de baixo
	private boolean positionExists(int row, int column) {
		// Quando uma posição existe no tabuleiro?
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// Metodo para ver se a posição o tabuleiro realmente existe
	// Tabuleiro é uma matriz 8x8, logo a posição (9,1) por exemplo, não existe
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	// Metodo para analisar se na posição especifica do tabuleiro existe já uma peça
	public boolean thereIsAPiece(Position position) {

		// Testando se essa posição no tabuleiro existe
		if (!positionExists(position)) {
			throw new BoardException("Posição inexistente no tabuleiro");
		}

		// Como faço para testar se tem uma peça em tal posição
		return piece(position) != null;

	}

}
