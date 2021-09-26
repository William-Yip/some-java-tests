import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class PaperRockScissor {

    final static String PAPER = "paper";
    final static String ROCK = "rock";
    final static String SCISSOR = "scissor";

    final static String[] ALL_MOVES = { PAPER, ROCK, SCISSOR };
    static final HashMap<String, String> WIN_AGAINST = new HashMap<>();

    static {
        WIN_AGAINST.put(PAPER, ROCK);
        WIN_AGAINST.put(ROCK, SCISSOR);
        WIN_AGAINST.put(SCISSOR, PAPER);
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("------------ ROCK, PAPER, SCISSOR ------------");
        System.out.println("Enter some option below to play:");
        System.out.println("0 - to select " + PAPER);
        System.out.println("1 - to select " + ROCK);
        System.out.println("2 - to select " + SCISSOR);
        System.out.println("9 - to exit");

        while (sc.hasNext()) {

            String option = sc.next().trim();
            try {
                int intOption = Integer.parseInt(option);

                if (intOption == 9)
                    System.exit(1);

                try {
                    String userMove = ALL_MOVES[intOption];

                    String npcMove = getNextMove();

                    String matchLabel = String.format(" %s (User) vS %s (Npc) ", userMove, npcMove);
                    System.out.println(matchLabel);

                    String feedback = String.format("USER %s", findMatchResult(userMove, npcMove));

                    System.out.println(feedback);

                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid option");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid option");
            }

        }

    }

    private static String findMatchResult(String userMove, String npcMove) {
        if (userMove.equals(npcMove))
            return "DRAW";

        String optionToWin = WIN_AGAINST.get(userMove);

        if (npcMove.equals(optionToWin))
            return "WINS";
        else
            return "LOSES";

    }

    private static String getNextMove() {
        int moveIndex = new Random().nextInt(ALL_MOVES.length);

        return ALL_MOVES[moveIndex];
    }

}