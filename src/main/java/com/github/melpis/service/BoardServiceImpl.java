package com.github.melpis.service;


import com.github.melpis.domain.Board;
import com.github.melpis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public List<Board> list() {
        return repository.findAll();
    }

    @Override
    public void writeBoard(Board board) {
        repository.save(board);
    }

    @Override
    public Board readBoard(Long seq) {

        Board returnBoard = repository.findOne(seq);
        returnBoard.increHits();

        return returnBoard;
    }

    @Override
    public void editBoard(Board board) {
        repository.save(board);
    }

    @Override
    public void deleteBoard(Long seq) {
        repository.delete(seq);
    }
}
