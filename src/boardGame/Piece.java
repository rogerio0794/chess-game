package boardGame;




public class Piece {
	
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
	
	
	
	
	
	

}
