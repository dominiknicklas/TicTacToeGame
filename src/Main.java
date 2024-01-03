public class Main {
    public static void main(String[] args) {
        System.out.println("""
                Engage in a quick and intense 3x3 Tic Tac Toe duel! 
                Take turns placing 'X' or 'O' on the grid to secure two consecutive wins and emerge victorious. 
                Outsmart your opponent in this fast-paced showdown!
                """);

        String player1 = Validation.validateName("Player 1");
        String player2 = Validation.validateName("Player 2");

        Game g1 = new Game(player1, player2);
        g1.showRoles();
        g1.play();
    }
}