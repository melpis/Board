package com.github.melpis.service;


import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {

    Page<Board> findAll(Pageable pageable);

    Board save(Board board, List<MultipartFile> file) throws IOException;

    Board findOne(Long id);

    void delete(Long id);

    Board readBoard(Long id);

    Board addComment(Long id, Comment comment);
}
