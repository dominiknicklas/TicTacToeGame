import java.util.Arrays;
import java.util.Scanner;
public class Game {
    private final String name1;
    private final String name2;
    private boolean n1Won = false;
    private boolean n2Won = false;
    private boolean draw = false;
    private boolean gameOver = false;
    int n1Overall = 0;
    int n2Overall = 0;
    int round = 1;
    private String[] field = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public Game(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public void showField() {
        System.out.println(field[0] + " | " + field[1] + " | " + field[2]);
        System.out.println("---------");
        System.out.println(field[3] + " | " + field[4] + " | " + field[5]);
        System.out.println("---------");
        System.out.println(field[6] + " | " + field[7] + " | " + field[8]);
    }

    public void showRoles() {
        System.out.println(name1 + " you are X");
        System.out.println(name2 + " you are Y");
    }
    public void play() {
        System.out.println("Round " + round);
        int random = randomize();
        int roundCounter = 1;
        if(random == 1) {
            while(!gameOver) {
                showField();
                if(roundCounter % 2 == 0) {
                    System.out.println(name1 + " its your turn, select the field you want to mark");
                    playerPlay("X");
                } else {
                    System.out.println(name2 + " its your turn, select the field you want to mark");
                    playerPlay("Y");
                }
                roundCounter++;
                gameOver = checkGameOver();
            }
        }
        if(random == 2) {
            while(!gameOver) {
                showField();
                if(roundCounter % 2 == 0) {
                    System.out.println(name2 + " its your turn, select the field you want to mark");
                    playerPlay("Y");
                } else {
                    System.out.println(name1 + " its your turn, select the field you want to mark");
                    playerPlay("X");
                }
                roundCounter++;
                gameOver = checkGameOver();
            }
        }

        showField();
        printResult();
        if(n1Overall == 2) {
            System.out.println(name1 + " won overall");
        } else if (n2Overall == 2) {
            System.out.println(name2 + " won overall");
        }else {
            resetField();
            resetWinner();
            round++;
            play();
        }
    }

    private void playerPlay(String player) {
        int number;
        do{
            number = Validation.enterInt() - 1;
        } while(number < 0 || number > 8);
        if(!field[number].equals("X") && !field[number].equals("Y")) {
            field[number] = player;
        } else {
            System.out.println("The selected field is already occupied - select a new one");
            playerPlay(player);
        }
    }

    private boolean checkGameOver() {
        for(int counter = 1; counter <= field.length; counter++) {
            String result = switch (counter) {
                case 1 -> field[0] + field[1] + field[2];
                case 2 -> field[3] + field[4] + field[5];
                case 3 -> field[6] + field[7] + field[8];
                case 4 -> field[0] + field[3] + field[6];
                case 5 -> field[1] + field[4] + field[7];
                case 6 -> field[2] + field[5] + field[8];
                case 7 -> field[0] + field[4] + field[8];
                case 8 -> field[2] + field[4] + field[6];
                default -> null;
            };

            if(result != null && result.equals("XXX")) {
                n1Won = true;
                n1Overall++;
                return gameOver = true;
            } else if (result != null && result.equals("YYY")) {
                n2Won = true;
                n2Overall++;
                return gameOver = true;
            }
        }
        for (int counter = 0; counter < 9; counter++) {
            if (Arrays.asList(field).contains(String.valueOf(counter + 1))) {
                break;
            }
            else if (counter == 8) {
                draw = true;
                return gameOver = true;
            }
        }
        return gameOver = false;
    }

    private void printResult() {
        if(n1Won) System.out.println(name1 + " won game " + round);
        if(n2Won) System.out.println(name2 + " won game " + round);
        if(draw) System.out.println("Nobody won, it's a draw!");
    }

    private int randomize() {
        return (Math.random() <= 0.5) ? 1 : 2;
    }

    private void resetField() {
        for (int counter = 0; counter < field.length; counter++) {
            field[counter] = String.valueOf(counter + 1);
        }
    }

    private void resetWinner() {
        n1Won = false;
        n2Won = false;
        draw = false;
        gameOver = false;
    }
}
