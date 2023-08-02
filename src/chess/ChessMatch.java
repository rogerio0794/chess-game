package chess;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board; // Uma partida de xadrex precisa de uma tabuleiro
	
	private int turn; // quem faz a jogada agora
	private Color currentPlayer; // Jogador atual
	
	
	
	private boolean check; // Ver se o rei está em check ou não
	
	
	
	
	// Lista de peças no tabuleiro e as capturadas
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	
	
	

	public ChessMatch() {
		board = new Board(8, 8); // Criando o tabuleiro 8x8 do xadrez		
		turn = 1; // Inicio da partida
		currentPlayer = Color.WHITE; // Jogador inicial é o com as peças brancas		
		initialSetup();
		check = false;
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	
	public boolean getCheck() {
		return check;
	}	
	
	// Metodo para trocar o turno
	private void nextTurn () {
		turn++;
		if (currentPlayer == Color.WHITE) {
			currentPlayer = Color.BLACK;
		} else {
			currentPlayer = Color.WHITE;
		}
		
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
	
	
	// Classe necessária para imprimir as posições que a peça pode se mover
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition(); // Convertendo posição do tabuleiro para posição da matriz
		validateSourcePosition(position); // Validando se a posição existe
		return board.piece(position).possibleMoves(); // Tabuleiro tem uma peça que tem seus possiveis movimentos
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
		
		
		// Testar se o jogador deixou seu proprio rei em xeque
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Movimento inválido, você deixou sem rei em xeque");
		}
		
		// Testar se o rei do adversário ficou em xeque
		if (testCheck(opponent(currentPlayer))) {
			check = true; // Rei adversário em xeque
		} else {
			check = false; //Rei não está em xeque
		}
		
		
		
		
		
		
		nextTurn();
		return (ChessPiece) capturedPiece;		
		
	}
	
	
	private void validateSourcePosition(Position position) {
		
		// Existência da posição de origem
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não existe peça na posição de origem");
		}
		
		// Verificar se o jogador esta escolhendo uma peça sua para mover e não a do adversário
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			// Pegando a cor do jogador atual e comparando com a peça no tabuleiro na posição que eu especifiquei
			throw new ChessException("A peça escolhida não é sua");
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
		
		// Removendo a peça da lista do tabuleiro e adicionando na lista das peças capturas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		
		// Colocando a peça da posição de origem na posição de destinho
		board.PlacePiece(p, target);
		return capturedPiece;
		
	}
	
	
	// Método de desfazer o movimento  caso eu mexa alguma peça e deixe meu Rei em xeque
	public void undoMove(Position source, Position target, Piece capturedPiece) {
		// Basicamente desfazer o que é feito no método makeMove (acima)
		
		// Tirando a peça que colocamos no destino
		Piece p = board.removePiece(target);
		// Devolvendo a peça para sua origem
		board.PlacePiece(p, source);
		
		
		// Se uma peça for capturada eu preciso voltar ela para sua posição que é a destino do movimento
		if (capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			
			// Tirar essa peça da lista de peças capturas e colocar na lista das peças do tabuleiros
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		
	}
	
	
	

	// Iniciar a partida de xadreex colocando as peças no tabuleiro pela posição da
	// peça no tabuleiro
	private void placenewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
//			poderia fazer assim
//			ChessPosition posicao = new ChessPosition(column, row);
//			board.PlacePiece(piece, posicao.toPosition();
	}
	
	
	// Método que devolve a cor do oponente
	private Color opponent (Color color) {
		if (color == Color.WHITE) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
		
	}

	

	
	// Método para localizar um rei de uma cor especifica
	private ChessPiece king(Color color) {
		
		// Filtrando a lista procurando as peças de uma cor espcifica
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == color).collect(Collectors.toList())	;
		// Necessidade de fazer um downcasting visto que o que tem cor é a ChessPiece e não a PIECE
		
		// Percorrer a lista e achar qual peça é um rei
		for (Piece p: list) {
			if (p instanceof King) { // Encontrei o rei
				
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("Não existe nenhum rei de cor " + color + " no tabuleiro");
	}
	
	
	// Método para testar se o rei está em check
	// Eu preciso percorrer todas as peças adversários do tabuleiro uma a uma e ver
	// Se essa peça consegue fazer um movimento que mate o rei
	// Testar se o rei da cor X está em cheque
	private boolean testCheck(Color color) {
		
		
		// Posição do rei em termos de matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		
		// Lista de peças do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList())	;
		
		// Agora vou testar cada peça dessa lista se ela consegue matar o rei
		for (Piece p: opponentPieces ) {
			boolean[][] mat = p.possibleMoves(); // Aqui temos os movimentos possiveis da peça p
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) { // Se essa matriz mat na posição do rei for true, siginifica q o rei está em xeque
				return true;
			}
		}
		return false;
		
	}
	
	

	// Iniciar a partida de xadreex colocando as peças no tabuleiro pela posição da
	// peça na matriz
	private void initialSetup() {
		// Aqui colocamos a peça de acordo com as posições to tabuleiro de xadrez e não
		// da matriz
		placenewPiece('b', 6, new Rook(board, Color.WHITE));
		placenewPiece('a', 8, new King(board, Color.WHITE));
		placenewPiece('f', 5, new King(board, Color.WHITE));
		placenewPiece('c', 1, new Rook(board, Color.BLACK));
		placenewPiece('e', 3, new King(board, Color.BLACK));


	}

}
