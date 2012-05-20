package cfgame;
public class Launcher {
	public static void main(String[] args) {
		//Connect4Game game = new Connect4Game();
		CFGameGrid game = new CFGameGrid("ConnectFour");
		game.game();
	}
}