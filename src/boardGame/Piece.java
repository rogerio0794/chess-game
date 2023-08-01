package boardGame;



//Piece ainda é muito genérica, eu não tenho como definir os movimentos das peças aqui
// Logo usa-se abstact
public abstract class Piece {
	
	protected Position position; // Toda peça tem uma posição	
	private Board board; // Associando a peça com um tabuleiro

	public Piece(Board board) {	
		this.board = board;
		position = null;  // Peça inicia em uma posição nula
	}

	// Apenas get, não vou deixar ninguem mudar o tabuleiro
	protected Board getBoard() {
		return board;
	}
	
	
	// Para validar os movimentos das peças, uma matriz do tamanho do tabuleiro, onde é true a peça pode ir
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	
	// Metodo para ver se é possivel algum movimento
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat =  possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	

}
