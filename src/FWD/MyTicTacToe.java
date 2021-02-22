package FWD;

import java.util.Scanner;

public class MyTicTacToe {

    private char board[][];

    private int ROWS = 0;
    private int COLUMNS = 0;
    int x = 0;

    private char playerRole;

    String winX = "";
    String winO = "";

    private enum gameStatus {
        WINNER,
        DRAW,
        PROCESS
    }

    private static Scanner input = new Scanner(System.in);

    public MyTicTacToe() {

        System.out.println("Welcome To Tic Tac Toe Game");

        do {
            System.out.print("Please input n*n board (odd number) : ");
            x = input.nextInt();
        } while (x % 2 == 0);

        ROWS = x;
        COLUMNS = x;
        board = new char[ROWS][COLUMNS];

        for (int i = 0; i < x; i++) {
            winX = winX + "X";
            winO = winO + "O";
        }

        do {
            System.out.print("Please input 1 to choose 'X' Character or 2 to choose 'O' Character : ");
            int character = input.nextInt();
            if (character == 1) {
                playerRole = 'X';
            } else if (character == 2) {
                playerRole = 'O';
            }
        } while (checkCharachterChoose(playerRole) == false);

        boardInisialized();
        showBoard();

    }

    //*************************************************************************//
    private void boardInisialized() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '?';
            }
        }
        char playerOne = 0;
        char playerTwo = 0;
        if (playerRole == 'X') {
            playerOne = 'X';
            playerTwo = 'O';
        } else if (playerRole == 'O') {
            playerOne = 'O';
            playerTwo = 'X';
        }
        System.out.println("PLAYER ONE: " + playerOne);
        System.out.println("PLAYER TWO: " + playerTwo);

    }

    //*************************************************************************//
    private boolean checkCharachterChoose(char playerRole) {
        boolean character = false;
        if (playerRole == 'X' || playerRole == 'O') {
            character = true;
        } else {
            character = false;
        }
        return character;
    }

    //*************************************************************************//
    private void showBoard() {
        for (int i = 0; i < ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
    }

    //*************************************************************************//
    private void switchPlayer() {
        if (playerRole == 'X') {
            playerRole = 'O';
        } else {
            playerRole = 'X';
        }
    }

    //*************************************************************************//
    private boolean winCheck() {
        return (rowCheck() || columnCheck() || diagonalCheck1() || diagonalCheck2());
    }

    private boolean rowCheck() {
        for (int i = 0; i < ROWS; i++) {
            String list = "";
            for (int j = 0; j < COLUMNS; j++) {
                list = list + board[i][j];
            }

            if (winCondition(list) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean columnCheck() {
        for (int i = 0; i < COLUMNS; i++) {
            String list = "";
            for (int j = 0; j < ROWS; j++) {
                list = list + board[j][i];
            }

            if (winCondition(list) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalCheck1() {
        String list = "";
        for (int i = 0; i < x; i++) {
            list = list + board[i][i];
        }

        return (winCondition(list) == true);
    }

    private boolean diagonalCheck2() {
        String list = "";
        for (int i = 0; i < x; i++) {
            int y = x-(i+1);
            list = list + board[y][i];
        }

        return (winCondition(list) == true);
    }

    private boolean winCondition(String list) {
        if (list.contains(winX) || list.contains(winO)) {
            return true;
        } else {
            return false;
        }
    }

    //*************************************************************************//
    private boolean drawCheck() {
        boolean draw = true;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == '?') {
                    draw = false;
                }
            }
        }
        return draw;
    }

    //*************************************************************************//
    private gameStatus nowStatus() {
        if (winCheck() == true) {
            return gameStatus.WINNER;
        } else if (drawCheck() == true) {
            return gameStatus.DRAW;
        } else {
            return gameStatus.PROCESS;
        }
    }

    private void showStatus() {
        gameStatus status = nowStatus();
        if (status == gameStatus.WINNER) {
            System.out.println("Player " +playerRole + " WIN!!");
        } else if (status == gameStatus.DRAW) {
            System.out.println("DRAW!!");
        }
    }

    //*************************************************************************//
    private void characterInput() {
        winCheck();
        drawCheck();
        nowStatus();

        System.out.print("Player " + playerRole + " please choose row (1-" + x + "): ");
        int row = input.nextInt() - 1;
        System.out.print("Player " + playerRole + " please choose column (1-" + x + "): ");
        int col = input.nextInt() - 1;

        if ((row < 0) || (row > (x - 1))) {
            System.out.println("Row is not valid, try again!");
        } else if ((col < 0) || (col > (x - 1))) {
            System.out.println("Column is not valid, try again!");
        } else if (board[row][col] != '?') {
            System.out.println("Already filled, try again!");
        } else {
            markBoard(row, col, playerRole);
            showBoard();
            if (nowStatus() == gameStatus.PROCESS) {
                switchPlayer();
            }

        }
    }

    //*************************************************************************//
    private void markBoard(int row, int column, char c) {
        board[row][column] = c;
    }

    //*************************************************************************//
    public void start() {
        do {
            characterInput();
        } while (nowStatus() == gameStatus.PROCESS);
        showStatus();
    }
}