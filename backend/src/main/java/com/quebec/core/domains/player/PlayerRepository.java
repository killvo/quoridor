package com.quebec.core.domains.player;

import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlayerRepository {
    private Map<UUID, Player> players;

    private final int MAX_PLAYERS_SIZE = 2;
    private final int MAX_WALLS_COUNT = 10;

    public PlayerRepository() {
        this.players = new HashMap<>();
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

    public Optional<Player> createNewPlayer(Role role) {
        if (role == null) {
            return Optional.empty();
        }
        if (players.size() == MAX_PLAYERS_SIZE) {
            return Optional.empty();
        }
        UUID id = UUID.randomUUID();
        Player player = players.put(id, new Player(id, MAX_WALLS_COUNT, role));
        if (player == null) {
            return Optional.empty();
        }
        return Optional.of(player);
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
        int availableWalls = player.getAvailableWallsCount();
        if (availableWalls == 0) {
            return Optional.empty();
        }
        player.setAvailableWallsCount(--availableWalls);
        var updatedPlayerOptional = update(player);
        if (updatedPlayerOptional.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(updatedPlayerOptional.get().getAvailableWallsCount());
    }

    public void removePlayers() {
        players.clear();
    }
}
