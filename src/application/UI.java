package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// CORES

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();	
		
	}
	
	// Ler uma posi��o do xadrex (letra + numero)
		public static ChessPosition readChessPosition(Scanner sc) {

			try {
				String s = sc.nextLine();
				char column = s.charAt(0);

				// Recortar meu string a partir da posi��o 1 e converter para inteiro
				int row = Integer.parseInt(s.substring(1));
				return new ChessPosition(column, row);			
			} 
			catch (Exception e) {
				throw new InputMismatchException("Erro na leitura da posi��o de xadrez. Valores validos s�o de a1 at� h8");
			}
		}

	// Imprimir as pe�as todas do tabuleiro
	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	// Imprimir as pe�as todas do tabuleiro e identificar para onde ela pode se mover
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {

		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	
	// Imprimir a partida (n�o s� o tabuleiro), exibir o turno e jogador atual
	public static void printMatch(ChessMatch chessMatch,  List<ChessPiece> captured  ) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();		
		System.out.println("Turno : " + chessMatch.getTurn());
		// Testar se o rei  est� em xeque
		if (!chessMatch.getCheckMatch()) {
			System.out.println("Aguardando jogador: " + chessMatch.getCurrentPlayer());	
			// Testar se o rei  est� em xeque
			if (chessMatch.getCheck()) {
				System.out.println("CHECK");
			}
		} else {
			System.out.println("CHECKMATE!");
			System.out.println("Vencedor " + chessMatch.getCurrentPlayer());
		}
		
		
		
	}
	
	
	// Imprimir pe�as capturadas
	private static void printCapturedPieces ( List<ChessPiece> capturedPieces) {
		
		// express�o lambda, stream e filtro
		// Vamos filtrar da lista todo mundo cuja a cor � branca
		List<ChessPiece> white = capturedPieces.stream().filter(x -> x.getColor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = capturedPieces.stream().filter(x -> x.getColor() == Color.BLACK).collect(Collectors.toList());
		
		
		System.out.println("Pe�as Capturadas");
		System.out.print("Branca: ");		
		System.out.print(ANSI_WHITE);			
		System.out.println(Arrays.toString(white.toArray())); // Imprimir os elementos da lista
		System.out.print(ANSI_RESET);
		
		System.out.print("Preta:  ");
		System.out.print(ANSI_YELLOW);			
		System.out.println(Arrays.toString(black.toArray())); // Imprimir os elementos da lista
		System.out.print(ANSI_RESET);
		
		
	}
	
	
	

	// Imprimir apenas uma pe�a e as posi��es que ela pode se mover (background)
	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		
		
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {

			// Colocando as cores nas pe�as
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}

		}
		System.out.print(" ");

	}

}
