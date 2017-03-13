package com.github.melpis.service;


import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    Page<Board> findAll(Pageable pageable);

    Board save(Board board, String username);

    Board findOne(Long id);

    void delete(Long id);

    Board readBoard(Long id);

    Board addComment(Long id, Comment comment);
}
