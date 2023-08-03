package chess;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardGame.Board;
import boardGame.Piece;
import boardGame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board; // Uma partida de xadrex precisa de uma tabuleiro
	
	private int turn; // quem faz a jogada agora
	private Color currentPlayer; // Jogador atual
	
	
	
	private boolean check; // Ver se o rei está em check ou não
	private boolean checkMatch; 
	
	
	
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
	
	public boolean getCheckMatch() {
		return checkMatch;
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
		
		
		
		// Testar o cheque mate para acabar o jogo
		if (testCheckMate(opponent(currentPlayer))) {
			checkMatch = true; 
		} else {
			checkMatch = false; 
			nextTurn();
		}
		
		
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
		ChessPiece p =(ChessPiece) board.removePiece(source);
		
		// Incrementando a variavel moveCount
		p.increaseMovecount();
		
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
		ChessPiece p = (ChessPiece) board.removePiece(target);
		
		// Decrementar a quantidade de movimentos
		p.decreaseMovecount();
		
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
	
	
	private boolean testCheckMate(Color color) {
		
		// Testar se ele ta em xeque primeiro
		if (!testCheck(color)) {
			return false;
		}
		
		// Criar uma lista com as minhas peças do tabuleiro
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == color).collect(Collectors.toList())	;
		
		
		// O que vai ser feito: Se tiver alguma peça minha que pode fazer algum movimento que tira o meu rei de check eu retorno false
		// Se esgosta meu for e não encontrar nenhum movimento possivel para isto, retorno true
		for (Piece p: list) {
			
			// Matriz com os movimentos possiveis da peça
			boolean[][] mat = p.possibleMoves(); 
			// Percorrer matriz
			for (int i =0; i <board.getRows();i++) {
				for (int j =0; j <board.getColumns();j++) {
					
					// Pegando as posições que é possivel o movimento
					if (mat[i][j]) {
						// Testando se esse movimento especifico tira o check
						// Como será feito: Eu vou colocar a peça p nesta posição especifica e testar o CHECK (APENAS CHECK)						
						// MOVENDO ENTÃO (primeiro pegando a posição de origem e destino)
						// getChessPosition pega a posção no xadrez e toPosition converte para matriz
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						
						// Fazendo o movimento
						Piece capturedPiece = makeMove(source, target);
						
						// Testando se o rei da minha cor ainda está em cheque depois do movimento
						boolean testCheck = testCheck(color);
						
						// Desfazer o movimento, só queriamos testar
						undoMove(source, target, capturedPiece);
						
						// Se testCheck é falso, significa que o rei não está em xeque então o xequeMate é falso
						if (!testCheck) {
							return false;
						}
						
					}
					
				}
				
			}
			
		}
		
		return true;
	}
	
	

	// Iniciar a partida de xadreex colocando as peças no tabuleiro pela posição da
	// peça na matriz
	private void initialSetup() {
		// Aqui colocamos a peça de acordo com as posições to tabuleiro de xadrez e não
		// da matriz
		placenewPiece('a', 1, new Rook(board, Color.WHITE));
		placenewPiece('h', 1, new Rook(board, Color.WHITE));
		placenewPiece('e', 1, new King(board, Color.WHITE));
		
		placenewPiece('a', 2, new Pawn(board, Color.WHITE));
		placenewPiece('b', 2, new Pawn(board, Color.WHITE));
		placenewPiece('c', 2, new Pawn(board, Color.WHITE));
		placenewPiece('d', 2, new Pawn(board, Color.WHITE));
		placenewPiece('e', 2, new Pawn(board, Color.WHITE));
		placenewPiece('f', 2, new Pawn(board, Color.WHITE));
		placenewPiece('g', 2, new Pawn(board, Color.WHITE));
		placenewPiece('h', 2, new Pawn(board, Color.WHITE));
		
		placenewPiece('c', 1, new Bishop(board, Color.WHITE));
		placenewPiece('f', 1, new Bishop(board, Color.WHITE));
		
		placenewPiece('b', 1, new Knight(board, Color.WHITE));
		placenewPiece('g', 1, new Knight(board, Color.WHITE));
		placenewPiece('d', 1, new Queen(board, Color.WHITE));
		
		
		
		placenewPiece('a', 8, new Rook(board, Color.BLACK));
		placenewPiece('h', 8, new Rook(board, Color.BLACK));
		placenewPiece('e', 8, new King(board, Color.BLACK));
		placenewPiece('a', 7, new Pawn(board, Color.BLACK));
		placenewPiece('b', 7, new Pawn(board, Color.BLACK));
		placenewPiece('c', 7, new Pawn(board, Color.BLACK));
		placenewPiece('d', 7, new Pawn(board, Color.BLACK));
		placenewPiece('e', 7, new Pawn(board, Color.BLACK));
		placenewPiece('f', 7, new Pawn(board, Color.BLACK));
		placenewPiece('g', 7, new Pawn(board, Color.BLACK));
		placenewPiece('h', 7, new Pawn(board, Color.BLACK));
		
		placenewPiece('c', 8, new Bishop(board, Color.BLACK));
		placenewPiece('f', 8, new Bishop(board, Color.BLACK));
		
		placenewPiece('b', 8, new Knight(board, Color.BLACK));
		placenewPiece('g', 8, new Knight(board, Color.BLACK));
		placenewPiece('d', 8, new Queen(board, Color.BLACK));


	}

}
