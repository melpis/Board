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
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.annotation.MultipartConfig;
import java.io.*;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@MultipartConfig(fileSizeThreshold = 20971520)
public class BoardServiceImpl implements BoardService{
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private AttachFileService attachFileService;

    @Override
    public Page<Board> findAll(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board save(Board board, List<MultipartFile> attachFiles) throws IOException {
        board.setDate(new Date());
        boardRepository.saveAndFlush(board);

        if(attachFiles != null && !attachFiles.isEmpty()) {
            attachFileService.registerFileList(board, attachFiles);
            boardRepository.save(board);
        }

        return boardRepository.findOne(board.getId());
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

        return boardRepository.save(board);
    }
}
