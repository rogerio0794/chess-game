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
	
	
	
	private boolean check; // Ver se o rei est� em check ou n�o
	private boolean checkMatch; 
	
	
	private ChessPiece enPassantVulnerable; // Pegar a pe�a que est� vulneravel para a jogada especial Passant
	
	
	
	// Lista de pe�as no tabuleiro e as capturadas
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	
	
	

	public ChessMatch() {
		board = new Board(8, 8); // Criando o tabuleiro 8x8 do xadrez		
		turn = 1; // Inicio da partida
		currentPlayer = Color.WHITE; // Jogador inicial � o com as pe�as brancas		
		initialSetup();
		check = false;
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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


	// Metodo para retornar a pe�a de xadrex dentro de uma posi��o especifica
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // Downcasting para compilador entender que n�o � do tipo
															// piece e sim ChessPiece

			}
		}
		return mat;

	}
	
	
	// Classe necess�ria para imprimir as posi��es que a pe�a pode se mover
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition(); // Convertendo posi��o do tabuleiro para posi��o da matriz
		validateSourcePosition(position); // Validando se a posi��o existe
		return board.piece(position).possibleMoves(); // Tabuleiro tem uma pe�a que tem seus possiveis movimentos
	}
	
	
	// Metodo para mover a pe�a de um lugar para outro
	public ChessPiece performChessMove(ChessPosition  sourcePosition, ChessPosition targetposition) {
		// Convertendo em ma posi�ao da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetposition.toPosition();
		
		// M�todo para validar posi��o de origem
		validateSourcePosition(source);
		
		// M�todo para validar posi��o de destino
		validadeTargetPosition(source,target);
		
		Piece capturedPiece = makeMove(source,target);
		
		
		
		// Guardando a pe�a que se moveu
		ChessPiece movedPiece = (ChessPiece) board.piece(target);
		
		
		
		
		// Testar se o jogador deixou seu proprio rei em xeque
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("Movimento inv�lido, voc� deixou sem rei em xeque");
		}
		
		// Testar se o rei do advers�rio ficou em xeque
		if (testCheck(opponent(currentPlayer))) {
			check = true; // Rei advers�rio em xeque
		} else {
			check = false; //Rei n�o est� em xeque
		}
		
		
		
		// Testar o cheque mate para acabar o jogo
		if (testCheckMate(opponent(currentPlayer))) {
			checkMatch = true; 
		} else {
			checkMatch = false; 
			nextTurn();
		}
		
		
		// En passant
		// Testar se a pe�a movida � um pe�o que moveu duas casas
		// Se sim ele ser� marcado como vulneravel para en passant
		if ( movedPiece   instanceof Pawn && (target.getRow() == source.getRow() -2 || target.getRow() == source.getRow() +2)) {
			 enPassantVulnerable = movedPiece;
		} else {
			enPassantVulnerable = null;
		}
		
		
		
		return (ChessPiece) capturedPiece;		
		
	}
	
	
	private void validateSourcePosition(Position position) {
		
		// Exist�ncia da posi��o de origem
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("N�o existe pe�a na posi��o de origem");
		}
		
		// Verificar se o jogador esta escolhendo uma pe�a sua para mover e n�o a do advers�rio
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			// Pegando a cor do jogador atual e comparando com a pe�a no tabuleiro na posi��o que eu especifiquei
			throw new ChessException("A pe�a escolhida n�o � sua");
		}
		
		
		// Existe movimento possiveis para pe�a?
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("N�o existe movimento possivel para pe�a escolhida");
		}
		
	}
	
	private void validadeTargetPosition(Position source, Position target) {
		// Testar se o movimento destino � possivel em rela��o a origem
		if (!board.piece(source).possibleMove(target)) { // A pe�a de origem no tabuleiro pode se movimentar para o destino especificado?
			throw new ChessException("A pe�a escolhida n�o pode se mover para posi��o de destino");
		}
	}
	
	
	
	public Piece makeMove(Position source, Position target) {
		
		// Retirando a pe�a da posi��o de origem
		ChessPiece p =(ChessPiece) board.removePiece(source);
		
		// Incrementando a variavel moveCount
		p.increaseMovecount();
		
		// Remover a possivel pe�a que est� na posi��o de destino
		Piece capturedPiece = board.removePiece(target);
		
		// Colocando a pe�a da posi��o de origem na posi��o de destinho
		board.PlacePiece(p, target);
		
		// Removendo a pe�a da lista do tabuleiro e adicionando na lista das pe�as capturas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		
		// Movimento especial: Roque
		
		// Roque pequeno, testando se a pe�a que queremos mover � um rei e a posi��o de destino � duas a direita da de origem 
		if (p instanceof King && target.getColumn() == source.getColumn() +2 ) {
			// Tenho que colocar a torre do lado do rei
			Position sourceT = new Position(source.getRow(), source.getColumn()+3); //Posi��o da torre			
			Position targetT = new Position(source.getRow(), source.getColumn()+1); // Posi��o que a torre vai ficar
			
			// Removendo a torre da posi��o de origem
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
			
			// Colocando na posi��o do roque pequeno
			board.PlacePiece(rook, targetT);
			rook.increaseMovecount();
			
		}
		
		
		// Roque grande, testando se a pe�a que queremos mover � um rei e a posi��o de destino � 2 a esquerda da de origem 
		if (p instanceof King && target.getColumn() == source.getColumn() - 2 ) {
			// Tenho que colocar a torre do lado do rei
			Position sourceT = new Position(source.getRow(), source.getColumn()-4); //Posi��o da torre			
			Position targetT = new Position(source.getRow(), source.getColumn()-1); // Posi��o que a torre vai ficar
			
			// Removendo a torre da posi��o de origem
			ChessPiece rook = (ChessPiece) board.removePiece(sourceT);
			
			// Colocando na posi��o do roque pequeno
			board.PlacePiece(rook, targetT);
			rook.increaseMovecount();
			
		}
		
		
		// Jogada especial en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn()  && capturedPiece == null   ) {
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					pawnPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				capturedPiece = board.removePiece(pawnPosition);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		
		
		
		
		
		
		
		return capturedPiece;
		
	}
	
	
	// M�todo de desfazer o movimento  caso eu mexa alguma pe�a e deixe meu Rei em xeque
	public void undoMove(Position source, Position target, Piece capturedPiece) {
		// Basicamente desfazer o que � feito no m�todo makeMove (acima)
		
		// Tirando a pe�a que colocamos no destino
		ChessPiece p = (ChessPiece) board.removePiece(target);
		
		// Decrementar a quantidade de movimentos
		p.decreaseMovecount();
		
		// Devolvendo a pe�a para sua origem
		board.PlacePiece(p, source);
		
		
		// Se uma pe�a for capturada eu preciso voltar ela para sua posi��o que � a destino do movimento
		if (capturedPiece != null) {
			board.PlacePiece(capturedPiece, target);
			
			// Tirar essa pe�a da lista de pe�as capturas e colocar na lista das pe�as do tabuleiros
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		
		
		// Movimento especial: Roque
		// Roque pequeno, testando se a pe�a que queremos mover � um rei e a posi��o de destino � duas a direita da de origem 
		if (p instanceof King && target.getColumn() == source.getColumn() +2 ) {
			// Tenho que colocar a torre do lado do rei
			Position sourceT = new Position(source.getRow(), source.getColumn()+3); //Posi��o da torre			
			Position targetT = new Position(source.getRow(), source.getColumn()+1); // Posi��o que a torre vai ficar
			
			// Removendo a torre da posi��o de DESTINO
			ChessPiece rook = (ChessPiece) board.removePiece(targetT);
			
			// Devolvendo a torre para posi��o de origem
			board.PlacePiece(rook, sourceT);
			rook.decreaseMovecount();
			
		}
		
		// Desfazendo o roque
		// Roque grande, testando se a pe�a que queremos mover � um rei e a posi��o de destino � 2 a esquerda da de origem 
		if (p instanceof King && target.getColumn() == source.getColumn() - 2 ) {
			// Tenho que colocar a torre do lado do rei
			Position sourceT = new Position(source.getRow(), source.getColumn()-4); //Posi��o da torre			
			Position targetT = new Position(source.getRow(), source.getColumn()-1); // Posi��o que a torre vai ficar
			
			// Removendo a torre da posi��o de DESTINO
			ChessPiece rook = (ChessPiece) board.removePiece(targetT);
			
			// Devolvendo a torre para posi��o de origem
			board.PlacePiece(rook, sourceT);
			rook.decreaseMovecount();
			
		}
		
		
		// Jogada especial en passant
		if (p instanceof Pawn) {
			if (source.getColumn() != target.getColumn()  && capturedPiece == enPassantVulnerable   ) {
				ChessPiece pawn = (ChessPiece) board.removePiece(target);
				Position pawnPosition;
				if (p.getColor() == Color.WHITE) {
					pawnPosition = new Position(3, target.getColumn());
				} else {
					pawnPosition = new Position(4, target.getColumn());
				}
				board.PlacePiece(pawn, pawnPosition);
			}
		}

		
		
		
		
		
	}
	
	
	

	// Iniciar a partida de xadreex colocando as pe�as no tabuleiro pela posi��o da
	// pe�a no tabuleiro
	private void placenewPiece(char column, int row, ChessPiece piece) {
		board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
//			poderia fazer assim
//			ChessPosition posicao = new ChessPosition(column, row);
//			board.PlacePiece(piece, posicao.toPosition();
	}
	
	
	// M�todo que devolve a cor do oponente
	private Color opponent (Color color) {
		if (color == Color.WHITE) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
		
	}

	

	
	// M�todo para localizar um rei de uma cor especifica
	private ChessPiece king(Color color) {
		
		// Filtrando a lista procurando as pe�as de uma cor espcifica
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == color).collect(Collectors.toList())	;
		// Necessidade de fazer um downcasting visto que o que tem cor � a ChessPiece e n�o a PIECE
		
		// Percorrer a lista e achar qual pe�a � um rei
		for (Piece p: list) {
			if (p instanceof King) { // Encontrei o rei
				
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("N�o existe nenhum rei de cor " + color + " no tabuleiro");
	}
	
	
	// M�todo para testar se o rei est� em check
	// Eu preciso percorrer todas as pe�as advers�rios do tabuleiro uma a uma e ver
	// Se essa pe�a consegue fazer um movimento que mate o rei
	// Testar se o rei da cor X est� em cheque
	private boolean testCheck(Color color) {
		
		
		// Posi��o do rei em termos de matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		
		// Lista de pe�as do oponente
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList())	;
		
		// Agora vou testar cada pe�a dessa lista se ela consegue matar o rei
		for (Piece p: opponentPieces ) {
			boolean[][] mat = p.possibleMoves(); // Aqui temos os movimentos possiveis da pe�a p
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) { // Se essa matriz mat na posi��o do rei for true, siginifica q o rei est� em xeque
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
		
		// Criar uma lista com as minhas pe�as do tabuleiro
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->  ((ChessPiece)x).getColor() == color).collect(Collectors.toList())	;
		
		
		// O que vai ser feito: Se tiver alguma pe�a minha que pode fazer algum movimento que tira o meu rei de check eu retorno false
		// Se esgosta meu for e n�o encontrar nenhum movimento possivel para isto, retorno true
		for (Piece p: list) {
			
			// Matriz com os movimentos possiveis da pe�a
			boolean[][] mat = p.possibleMoves(); 
			// Percorrer matriz
			for (int i =0; i <board.getRows();i++) {
				for (int j =0; j <board.getColumns();j++) {
					
					// Pegando as posi��es que � possivel o movimento
					if (mat[i][j]) {
						// Testando se esse movimento especifico tira o check
						// Como ser� feito: Eu vou colocar a pe�a p nesta posi��o especifica e testar o CHECK (APENAS CHECK)						
						// MOVENDO ENT�O (primeiro pegando a posi��o de origem e destino)
						// getChessPosition pega a pos��o no xadrez e toPosition converte para matriz
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						
						// Fazendo o movimento
						Piece capturedPiece = makeMove(source, target);
						
						// Testando se o rei da minha cor ainda est� em cheque depois do movimento
						boolean testCheck = testCheck(color);
						
						// Desfazer o movimento, s� queriamos testar
						undoMove(source, target, capturedPiece);
						
						// Se testCheck � falso, significa que o rei n�o est� em xeque ent�o o xequeMate � falso
						if (!testCheck) {
							return false;
						}
						
					}
					
				}
				
			}
			
		}
		
		return true;
	}
	
	

	// Iniciar a partida de xadreex colocando as pe�as no tabuleiro pela posi��o da
	// pe�a na matriz
	private void initialSetup() {
		// Aqui colocamos a pe�a de acordo com as posi��es to tabuleiro de xadrez e n�o
		// da matriz
		placenewPiece('a', 1, new Rook(board, Color.WHITE));
		placenewPiece('h', 1, new Rook(board, Color.WHITE));
		placenewPiece('e', 1, new King(board, Color.WHITE, this)); //  this, esta mesma partida
		
		placenewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placenewPiece('h', 2, new Pawn(board, Color.WHITE, this));
		
		placenewPiece('c', 1, new Bishop(board, Color.WHITE));
		placenewPiece('f', 1, new Bishop(board, Color.WHITE));
		
		placenewPiece('b', 1, new Knight(board, Color.WHITE));
		placenewPiece('g', 1, new Knight(board, Color.WHITE));
		placenewPiece('d', 1, new Queen(board, Color.WHITE));
		
		
		
		placenewPiece('a', 8, new Rook(board, Color.BLACK));
		placenewPiece('h', 8, new Rook(board, Color.BLACK));
		placenewPiece('e', 8, new King(board, Color.BLACK, this));
		placenewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placenewPiece('h', 7, new Pawn(board, Color.BLACK, this));
		
		placenewPiece('c', 8, new Bishop(board, Color.BLACK));
		placenewPiece('f', 8, new Bishop(board, Color.BLACK));
		
		placenewPiece('b', 8, new Knight(board, Color.BLACK));
		placenewPiece('g', 8, new Knight(board, Color.BLACK));
		placenewPiece('d', 8, new Queen(board, Color.BLACK));


	}

}
