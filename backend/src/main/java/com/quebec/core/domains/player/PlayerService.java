package com.quebec.core.domains.player;

import com.quebec.core.domains.player.exceptions.IncorrectIdException;
import com.quebec.core.domains.player.exceptions.IncorrectPlayerException;
import com.quebec.core.domains.player.exceptions.IncorrectRoleException;
import com.quebec.core.domains.player.model.Player;
import com.quebec.core.domains.player.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    private PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(UUID id) {
        if (id == null) {
            throw new IncorrectIdException("Incorrect ID. NULL value");
        }
        return playerRepository.getById(id).orElseThrow();
    }

    public Player createNewPlayer(Role role) {
        if (role == null) {
            throw new IncorrectRoleException("Incorrect Role. NULL value");
        }
        return playerRepository.createNewPlayer(role).orElseThrow();
    }

    public Player updatePlayer(Player player) {
        if (isPlayerIncorrect(player)) {
            throw new IncorrectPlayerException("Player or player fields are null");
        }
        return playerRepository.update(player).orElseThrow();
    }

    public int decreaseWallsCount(UUID id) {
        if (id == null) {
            throw new IncorrectIdException("Incorrect ID. NULL value");
        }
        return playerRepository.decreaseWallsCount(id).orElseThrow();
    }

    private boolean isPlayerIncorrect(Player player) {
        return player == null || player.getId() == null || player.getAvailableWallsAmount() == null || player.getRole() == null;
    }

    public Optional<Player> getBotPlayer() {
        Collection<Player> players = playerRepository.getAll().orElseThrow();
        return players.stream()
                .filter(player -> player.getRole() == Role.BOT)
                .findAny();
    }

    public void removePlayers() {
        playerRepository.removePlayers();
    }
}
