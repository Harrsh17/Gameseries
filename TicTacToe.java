import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TicTacToe {
    private static String board[][] = new String[3][3];
    private static String currentplayer, player1, player2;
    private static Map<String, String> playerinfo = new HashMap<>();
    private static Set<Map<Integer, Integer>> movetracker = new HashSet<>();

    public static void main(String arrgs[]) {

        loadPlayer();
        startGame();
    }

    public static void loadBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    board[i][j] = " ";  // Initialize with empty space
                }
                System.out.print("|"+board[i][j]);
            }
            System.out.println("\n");
        }
    }

    public static void loadPlayer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Player 1 Name");
        player1 = sc.nextLine();
        System.out.println("Enter Player 2 Name");
        player2 = sc.nextLine();
        playerinfo.put(player1, "X");
        playerinfo.put(player2, "O");
        System.out.println(
                player1 + " is " + playerinfo.get(player1) + "  " + player2 + " is " + playerinfo.get(player2));
    }

    public static void assignMark(int x, int y) {
        String marker = playerinfo.get(currentplayer);
        for (int i = x; i <= x; i++) {
            for (int j = y; j <= y; j++) {
               if(board[i][y] == null || board[i][y] == " ")
               {board[i][y] = marker;} 
            }
        }
    }

    public static void trackMove(int x, int y) {
        Map<Integer, Integer> move = new HashMap();
        Scanner sc = new Scanner(System.in);
        boolean moveExist = false;
        for(Map<Integer, Integer> m : movetracker)
        {
            System.out.println("Move Tracker Keys :" + m.keySet() + " Move Tracker" + m.values());
        }
        for (Map<Integer, Integer> m : movetracker) {
            for(Map.Entry<Integer, Integer> entry : m.entrySet()) {
                if (entry.getKey() == x && entry.getValue() == y){
                    System.out.println("This position can not be marked , please select some other position");
                    moveExist = true;
                }
            }
        }
        if(!moveExist) {
            move.put(x, y);
            movetracker.add(move);
            System.out.println("Great ,your position is marked , Next Player please select your position");
        }

        if (movetracker.isEmpty())
        {
            move.put(x, y);
            movetracker.add(move);
            System.out.println("Great the first position is marked , Next Player please select your position");
            assignMark(x, y);
        }


        while (moveExist) {
            x = sc.nextInt();
            y = sc.nextInt();
            trackMove(x, y);
            moveExist = false;
        }
        assignMark(x, y);
    }

    public static void togglePlayer() {
        if (currentplayer == null) {
            currentplayer = player1;
        } else if (currentplayer.equals(player1)) {
            currentplayer = player2;
        } else {
            currentplayer = player1;
        }

        System.out.println("Current Player is " + currentplayer + " Please Enter your row and column");
    }

    public static void startGame()
    {
        System.out.println("Lets Begin the Game");
        loadBoard();
        boolean gameover = false, moveExist = false, drawcheck = false;
        Scanner sc = new Scanner(System.in);

        while (!gameover && !drawcheck) {
            togglePlayer();
            int x = sc.nextInt();
            int y = sc.nextInt();
            trackMove(x, y);
            loadBoard();
            gameover = checkWin(currentplayer);
            drawcheck = checkDraw();
            
        }
        if(drawcheck == true && gameover == false)
        {
            System.out.println("Game Over" + " " + "It's a Draw");
        }
        else
        {
            System.out.println("Game Over  " + " " + currentplayer + " is the winner");
        }

    }

    public static boolean checkWin(String currentplayer) {
        String marker = playerinfo.get(currentplayer);
        for (int i = 0; i <= 2; i++) {
            // Check rows with null safety
            if (marker.equals(board[i][0]) && marker.equals(board[i][1]) && marker.equals(board[i][2])) {
                return true;
            }
            // Check columns with null safety
            if (marker.equals(board[0][i]) && marker.equals(board[1][i]) && marker.equals(board[2][i])) {
                return true;
            }
        }
        // Check diagonals with null safety
        if (marker.equals(board[0][0]) && marker.equals(board[1][1]) && marker.equals(board[2][2])) {
            return true;
        }
        if (marker.equals(board[0][2]) && marker.equals(board[1][1]) && marker.equals(board[2][0])) {
            return true;
        }
        
        return false;
    }
    public static boolean checkDraw(){
        boolean draw = false;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                if(board[i][j] == " ") return draw;
            }
        }
        System.out.println(draw);
        draw = true;
        return  draw;
    }
}
