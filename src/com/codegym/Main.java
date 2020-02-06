package com.codegym;

import com.codegym.board.Board;
import com.codegym.chess.Bishop;
import com.codegym.common.Coordinate;

import java.util.Scanner;

public class Main {
    public static Board board = new Board();

    public static void main(String[] args) throws Exception {
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào vị trí quân tượng: ");
        String posString = scanner.nextLine();

        Coordinate firstPosition = Board.convertStringToCoordinate(posString);
        Bishop bishop = new Bishop();
        board.setChessmanAt(bishop, firstPosition);
        System.out.println(board);

        System.out.println("Nhập vào vị trí nước đi: ");
        String nextPosString = scanner.nextLine();


        Coordinate nextPosition = Board.convertStringToCoordinate(nextPosString);

        // if (isValidBishopMove(firstPosition, nextPosition)) {
        if (bishop.isValidNextCoordinate(nextPosition)) {
            System.out.println("Nước đi hợp lệ của quân tượng");
        } else {
            System.out.println("Nước đi không hợp lệ của quân tượng");
        }
    }

    static boolean isValidBishopMove(Coordinate firstPosition, Coordinate nextPosition) throws Exception {
        return board.canMoveChessman(firstPosition, nextPosition);
    }

}
