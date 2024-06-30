package gr.aueb.cf.masterpeakers.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Cart {
    private List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void clear() {
        games.clear();
    }

    public double getTotal() {
        return games.stream().mapToDouble(Game::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "games=" + games +
                '}';
    }
}
