package snake.game.game;

import snake.game.model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RankingManager {

    private static final String PATH_NAME = "archive.bin";
    private List<Player> ranking = new ArrayList<>();

    public RankingManager() {
        loadRanking();
    }

    private void loadRanking() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(PATH_NAME));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ranking = (List<Player>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("No archive file!");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void savePlayer(Player player) {
        ranking.add(player);
        try {
            FileOutputStream fileInputStream = new FileOutputStream(new File(PATH_NAME));
            ObjectOutput objectInputStream = new ObjectOutputStream(fileInputStream);
            objectInputStream.writeObject(ranking);
            objectInputStream.flush();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("No archive file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Player> getRanking() {
        return ranking;
    }
}
