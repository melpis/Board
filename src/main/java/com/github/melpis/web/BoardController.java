package com.github.melpis.web;

import com.github.melpis.domain.Board;
import com.github.melpis.domain.Comment;
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
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardServiceImpl boardService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model,
                             @PageableDefault(sort={"id"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<Board> boardListPage = boardService.findAll(pageable);
        model.addAttribute("boardListPage", boardListPage);

        return "board/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeBoardForm() {

        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writeBoardProcess(@RequestBody Board board) {
        boardService.save(board);

        return "redirect:/board/read/{" + board.getId() + "}";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
    public String readBoard(@PathVariable("id") Long id, Model model) {
        Board board = boardService.readBoard(id);
        model.addAttribute("board", board);

        return "board/read/{" + id + "}";
    }

    @RequestMapping(value = "/read/{id}", method = RequestMethod.POST)
    public String writeComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        boardService.addComment(id, comment);

        return "redirect:board/read/{" + id + "}";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBoardForm(@PathVariable("id") Long id, Model model) {
        Board editBoard = boardService.findOne(id);
        model.addAttribute("editBoard", editBoard);

        return "board/edit/{" + id + "}";
    }

    @RequestMapping(value = "/edit/{seq}", method = RequestMethod.POST)
    public String editBoardProcess(@RequestBody Board board) {
        boardService.save(board);

        return "redirect:/board/read/{" + board.getId() + "}";
    }

    @RequestMapping(value = "/delete/{seq}", method = RequestMethod.GET)
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.delete(id);

        return "redirect:/board/list";
    }
}
