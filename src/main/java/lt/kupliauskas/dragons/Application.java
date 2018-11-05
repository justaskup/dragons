package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    Bot gameBot;

    @Autowired
    public Application(Bot gameBot) {
        this.gameBot = gameBot;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run (String[] args) {
        Game game = gameBot.playGame();
        System.out.println(game.toString());

    }
}
