package com.github.melpis.service;


import com.github.melpis.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {

    Page<Board> findAll(Pageable pageable);

    void writeBoard(Board board);

    Board readBoard(Long seq);

    void editBoard(Board board);

    void deleteBoard(Long seq);

    Board findOne(Long seq);


}
