package com.quebec.core.domains.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardUnitTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void whenBoardInit_shouldSucceed() {
        boardRepository.initBoard();

    }
}
