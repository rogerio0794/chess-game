package application;

import java.util.Scanner;

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
		
			// Imprimir o tabuleiro do xadrex
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);
			
			
			
			System.out.println();
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		
		}
	
	}

}
