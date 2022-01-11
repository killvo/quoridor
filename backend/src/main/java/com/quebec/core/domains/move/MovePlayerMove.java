package com.quebec.core.domains.move;

import com.quebec.core.domains.move.dto.MakeMoveRequest;
import com.quebec.core.domains.move.dto.MakeMoveResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovePlayerMove extends Move {
    private int x;
    private int y;

    public MakeMoveRequest toRequest(UUID userId) {
        return new MakeMoveRequest(userId, x, y);
    }

    public MakeMoveResponse toResponse(UUID userId) {
        return new MakeMoveResponse(userId, x, y, null);
    }
}
