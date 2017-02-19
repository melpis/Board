package com.github.melpis.service;


import com.github.melpis.domain.Board;
import com.github.melpis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public List<Board> list() {
        return repository.list();
    }

    @Override
    public void writeBoard(Board board) {
        repository.insertBoard(board);
    }

    @Override
    public Board readBoard(Long seq) {

        Board returnBoard = repository.getBoard(seq);
        returnBoard.increHits();

        return returnBoard;
    }

    @Override
    public void editBoard(Board board) {
        repository.editBoard(board);
    }

    @Override
    public void deleteBoard(Long seq) {
        repository.deleteBoard(seq);
    }
}
