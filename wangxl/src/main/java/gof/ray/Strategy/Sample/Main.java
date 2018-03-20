package gof.ray.Strategy.Sample;
public class Main {
    public static void main(String[] args) {
        
        int seed1 = 314; //随机数的种子
        int seed2 = 15;  //随机数的种子
        
        Player player1 = new Player("player_1", new WinningStrategy(seed1));
        Player player2 = new Player("player_2", new ProbStrategy(seed2));
        
        for (int i = 0; i < 100; i++) {
            Hand nextHand1 = player1.nextHand();
            Hand nextHand2 = player2.nextHand();
            if (nextHand1.isStrongerThan(nextHand2)) {
                player1.win();
                player2.lose();
                System.out.println("胜利者:" + player1);
            } else if (nextHand2.isStrongerThan(nextHand1)) {
                player1.lose();
                player2.win();
                System.out.println("胜利者:" + player2);
            } else {
                System.out.println("平局...");
                player1.even();
                player2.even();
            }
        }
        System.out.println("Total result:");
        System.out.println(player1.toString());
        System.out.println(player2.toString());
    }
}
