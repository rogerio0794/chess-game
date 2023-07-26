package boardGame;




public class Piece {
	
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
	
	
	
	
	
	

}
