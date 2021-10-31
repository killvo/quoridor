package com.quebec.core.domains.player;

import com.quebec.core.domains.player.model.FinishLine;
import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PlayerRepository {
    private final Map<UUID, Player> players;

    private final int MAX_PLAYERS_SIZE = 2;
    private final int MAX_WALLS_COUNT = 10;

    public PlayerRepository() {
        this.players = new ConcurrentHashMap<>();
    }

    public Optional<Collection<Player>> getAll() {
        return Optional.of(players.values());
    }

    public Optional<Player> getById(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        Player player = players.get(id);
        if (player == null) {
            return Optional.empty();
        }
        return Optional.of(player);
    }

    public Optional<Player> createNewPlayer(Role role, String[] finishLine) {
        if (role == null) {
            return Optional.empty();
        }
        if (players.size() == MAX_PLAYERS_SIZE) {
            return Optional.empty();
        }
        final UUID id = UUID.randomUUID();

        Player playerToAdd = new Player(id, MAX_WALLS_COUNT, role, finishLine);
        synchronized (players) {
            players.put(id, playerToAdd);
        }
        return Optional.of(playerToAdd);
    }

    public Optional<Player> update(Player player) {
        if (player == null) {
            return Optional.empty();
        }
        if (player.getId() != null && !players.containsKey(player.getId())) {
            return Optional.empty();
        }
        players.put(player.getId(), player);

        return Optional.of(player);
    }

    public Optional<Integer> decreaseWallsCount(UUID id) {
        var playerOptional = getById(id);
        if (playerOptional.isEmpty()) {
            return Optional.empty();
        }
        Player player = playerOptional.get();
        int availableWalls = player.getAvailableWallsAmount();
        if (availableWalls == 0) {
            return Optional.empty();
        }
        player.setAvailableWallsAmount(--availableWalls);
        var updatedPlayerOptional = update(player);
        if (updatedPlayerOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(updatedPlayerOptional.get().getAvailableWallsAmount());
    }

    public void removePlayers() {
        players.clear();
    }

    public List<Player> renewPlayers() {
        List<UUID> oldIds = new ArrayList<>(players.keySet());
        var player1 = getById(oldIds.get(1)).orElseThrow();
        player1.setAvailableWallsAmount(MAX_WALLS_COUNT);
        var player2 = getById(oldIds.get(0)).orElseThrow();
        player2.setAvailableWallsAmount(MAX_WALLS_COUNT);
        players.clear();
        players.put(player1.getId(), player1);
        players.put(player2.getId(), player2);
        return List.of(player1, player2);
    }
}
