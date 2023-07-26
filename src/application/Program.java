package application;

import boardGame.Board;
import chess.ChessMatch;

public class Program {
	
	
	public static void main(String[] args) {
		
		System.out.println("Hello World!");
		
//		Board board = new Board(8,8); // Implementando um tabuleiro 8x8
		
		
		// Intanciar uma partida de xadrex
		ChessMatch chessMatch = new ChessMatch();
		
		// Imprimir o tabuleiro do xadrex
		UI.printBoard(chessMatch.getPieces());
	
	}

}
