package com.github.melpis.service;


import com.github.melpis.domain.Board;

import java.util.List;

public interface BoardService {

    List<Board> list();
    // List<Board> list(int page);

    void writeBoard(Board board);

    Board readBoard(Long seq);

    void editBoard(Board board);

    void deleteBoard(Long seq);

}
