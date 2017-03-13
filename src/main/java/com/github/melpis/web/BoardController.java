package com.github.melpis.web;

import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
import com.github.melpis.domain.User;
import com.github.melpis.service.BoardServiceImpl;
import com.github.melpis.service.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    private BoardServiceImpl boardService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Page<Board> list(@PageableDefault(sort={"id"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<Board> boardListPage = boardService.findAll(pageable);

        return boardListPage;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public void writeBoardForm() {

    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public Board writeBoardProcess(@RequestBody Board board, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return boardService.save(board, userDetails.getUsername());
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public Board readBoard(@PathVariable("id") Long id) {

        return boardService.readBoard(id);
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public Board writeComment(@PathVariable("id") Long id, @RequestBody Comment comment) {

        return boardService.addComment(id, comment);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public Board editBoardForm(@PathVariable("id") Long id) {

        return boardService.findOne(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public Board editBoardProcess(@PathVariable Long id, @RequestBody Board board,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        board.setId(id);

        return boardService.save(board, userDetails.getUsername());
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void deleteBoard(@PathVariable("id") Long id) {

        boardService.delete(id);
    }
}
