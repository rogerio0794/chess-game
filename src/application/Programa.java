package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Programa {

	public static void main(String[] args) {

//		System.out.println("Hello World!");

//		Board board = new Board(8,8); // Implementando um tabuleiro 8x8

		Scanner sc = new Scanner(System.in);

		// Intanciar uma partida de xadrex
		ChessMatch chessMatch = new ChessMatch();
		
		// Lista de peças
		List<ChessPiece> captured = new ArrayList<>();

		
		// Enquanto checkMate for falso roda o loop
		while (!chessMatch.getCheckMatch()) {

			try {

//				UI.clearScreen();

				// Imprimir o tabuleiro do xadrex
				UI.printMatch(chessMatch,captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				// Desenha no tabuleiro as posições que a peça pode se mover
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.printBoard(chessMatch.getPieces(),possibleMoves);
				
				

				System.out.println();
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				
				// Adicionando a peça capturada na lista de peças capturas
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				
				// Promoção, troca de peça
				if (chessMatch.getPromoted() != null) {
					System.out.print("Entre com a peça para promoção (B/N/R/Q): ");
					String type = sc.nextLine().toUpperCase();
					// Validação
					while (!type.equals("B")  && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
						System.out.print("Valor invalido!. Entre com a peça para promoção (B/N/R/Q): ");
						type = sc.nextLine().toUpperCase();
					}					
					chessMatch.replacePromotedPiece(type);
				}
				

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			}

		}
		
//		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	} 

}
