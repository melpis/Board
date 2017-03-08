package com.github.melpis.service;

import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
import com.github.melpis.repository.BoardRepository;
import com.github.melpis.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.Date;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board save(Board board) {
        board.setDate(new Date());

        return boardRepository.save(board);
    }

    @Override
    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        boardRepository.delete(id);
    }

    @Override
    public Board readBoard(Long id) {
        Board board = boardRepository.findOne(id);
        board.increaseHits();
        boardRepository.save(board);

        return board;
    }

    public Board addComment(Long id, Comment comment) {

        Board board = findOne(id);
        comment.setDate(new Date());
        comment.setBoard(board);
        System.out.println("ADD COMMENT");

        return boardRepository.save(board);
    }
}
