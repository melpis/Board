package com.github.melpis.web;

import com.github.melpis.domain.AttachFile;
import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
import com.github.melpis.service.AttachFileServiceImpl;
import com.github.melpis.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;
    @Autowired
    private AttachFileServiceImpl attachFileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page<Board> list(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<Board> boardListPage = boardService.findAll(pageable);

        return boardListPage;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public void writeBoardForm() {

    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public Board writeBoardProcess(@RequestBody Board board,
                                   @RequestParam(name = "files", required=false) List<MultipartFile> files) throws IOException {

        return boardService.save(board, files);
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public Board readBoard(@PathVariable("id") Long id) {

        return boardService.readBoard(id);
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public Board writeComment(@PathVariable("id") Long id, @RequestBody Comment comment) {

        return boardService.addComment(id, comment);
    }

    @RequestMapping(value = "/read/{id}/{fileId}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") Long id, @PathVariable("fileId") Long fileId,
                             HttpServletResponse response) {
        AttachFile downFile = attachFileService.findOne(fileId);
        attachFileService.downloadFile(response, downFile);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public Board editBoardForm(@PathVariable("id") Long id) {

        return boardService.findOne(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public Board editBoardProcess(@PathVariable Long id, @RequestBody Board board,
                                  @RequestParam(name = "files", required=false) List<MultipartFile> files) throws IOException {
        board.setId(id);

        return boardService.save(board, files);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void deleteBoard(@PathVariable("id") Long id) {

        Board deletedBoard = boardService.findOne(id);
        attachFileService.removeFile(deletedBoard.getAttachFiles());
        boardService.delete(id);
    }
}
