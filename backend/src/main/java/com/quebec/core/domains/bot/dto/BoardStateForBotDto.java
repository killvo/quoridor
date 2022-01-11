package com.quebec.core.domains.bot.dto;

import com.quebec.core.domains.move.model.Orientation;
import com.quebec.core.domains.player.model.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardStateForBotDto {
    private Player player;
    private Graph<String, DefaultEdge> board;
    private Map<UUID, String> playersPositions;
    private Orientation[][] walls;
}
