package com.github.melpis.service;


import com.github.melpis.domain.Board;
import com.github.melpis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository repository;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void writeBoard(Board board) {
        repository.save(board);
    }

    @Override
    public Board readBoard(Long seq) {

        Board returnBoard = repository.findOne(seq);
        returnBoard.increHits();
        repository.save(returnBoard);

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

    @Override
    public Board findOne(Long seq) {
        return repository.findOne(seq);
    }


}
