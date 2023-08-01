package chess;



import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board; // Uma partida de xadrex precisa de uma tabuleiro

	public ChessMatch() {
		board = new Board(8, 8); // Criando o tabuleiro 8x8 do xadrez
		initialSetup();
	}

	

	// Metodo para retornar a peça de xadrex dentro de uma posição especifica
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // Downcasting para compilador entender que não é do tipo
															// piece e sim ChessPiece

			}
		}
		return mat;

	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	
	// Metodo para mover a peça de um lugar para outro
	public ChessPiece performChessMove(ChessPosition  sourcePosition, ChessPosition targetposition) {
		// Convertendo em ma posiçao da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetposition.toPosition();
		
		// Método para validar posição de origem
		validateSourcePosition(source);
		
		// Método para validar posição de destino
		validadeTargetPosition(source,target);
		
		Piece capturedPiece = makeMove(source,target);
		return (ChessPiece) capturedPiece;		
		
	}
	
	
	private void validateSourcePosition(Position position) {
		
		// Existência da posição de origem
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		
		// Existe movimento possiveis para peça?
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("Não existe movimento possivel para peça escolhida");
		}
		
	}
	
	private void validadeTargetPosition(Position source, Position target) {
		// Testar se o movimento destino é possivel em relação a origem
		if (!board.piece(source).possibleMove(target)) { // A peça de origem no tabuleiro pode se movimentar para o destino especificado?
			throw new ChessException("A peça escolhida não pode se mover para posição de destino");
		}
	}
	
	
	
	public Piece makeMove(Position source, Position target) {
		
		// Retirando a peça da posição de origem
		Piece p =board.removePiece(source);
		
		// Remover a possivel peça que está na posição de destino
		Piece capturedPiece = board.removePiece(target);
		
		
		// Colocando a peça da posição de origem na posição de destinho
		board.PlacePiece(p, target);
		return capturedPiece;
		
	}
	
	
	

	// Iniciar a partida de xadreex colocando as peças no tabuleiro pela posição da
	// peça no tabuleiro
	private void placenewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition());

//			poderia fazer assim
//			ChessPosition posicao = new ChessPosition(column, row);
//			board.PlacePiece(piece, posicao.toPosition();
	}

	// Iniciar a partida de xadreex colocando as peças no tabuleiro pela posição da
	// peça na matriz
	private void initialSetup() {
		// Aqui colocamos a peça de acordo com as posições to tabuleiro de xadrez e não
		// da matriz
		placenewPiece('b', 6, new Rook(board, Color.WHITE));
		placenewPiece('a', 8, new King(board, Color.WHITE));
		placenewPiece('c', 1, new Rook(board, Color.BLACK));
		placenewPiece('e', 3, new King(board, Color.BLACK));

//		placenewPiece('e',3, new King(board, Color.BLACK));
//		placenewPiece('p',3, new King(board, Color.BLACK));

//		board.PlacePiece(new Rook(board, Color.WHITE), new Position(4,1));
//		board.PlacePiece(new King(board, Color.WHITE), new Position(5,1));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(6,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(0,0));

		// Ver os erros
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(6,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(9,4));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(9,9));
//		board.PlacePiece(new Rook(board, Color.BLACK), new Position(0,9));

		// Perceba que as posições são correspondentes a matriz e não o tabuleiro
		// posioção 8a do tabuleiro corresponde a 0,0 da matriz
	}

}
