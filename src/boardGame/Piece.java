package boardGame;



//Piece ainda � muito gen�rica, eu n�o tenho como definir os movimentos das pe�as aqui
// Logo usa-se abstact
public abstract class Piece {
	
	protected Position position; // Toda pe�a tem uma posi��o	
	private Board board; // Associando a pe�a com um tabuleiro

	public Piece(Board board) {	
		this.board = board;
		position = null;  // Pe�a inicia em uma posi��o nula
	}

	// Apenas get, n�o vou deixar ninguem mudar o tabuleiro
	protected Board getBoard() {
		return board;
	}
	
	
	// Para validar os movimentos das pe�as, uma matriz do tamanho do tabuleiro, onde � true a pe�a pode ir
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	
	// Metodo para ver se � possivel algum movimento
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
