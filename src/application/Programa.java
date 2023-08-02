package application;

import java.util.InputMismatchException;
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

		while (true) {

			try {

//				UI.clearScreen();

				// Imprimir o tabuleiro do xadrex
				UI.printMatch(chessMatch);
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

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();

			}

		}

	} 

}
