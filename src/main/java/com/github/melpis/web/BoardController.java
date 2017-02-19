package com.github.melpis.web;

import com.github.melpis.domain.Board;
import com.github.melpis.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardServiceImpl boardService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("board/list");
        mav.addObject("list", boardService.list());
        return mav;
    }
    /*
    @RequestMapping(value = "/list/{page}")
    public String list(@PathVariable("page") int page, Model model) {
        return "list";
    }*/

    @RequestMapping(value = "/write")
    public String writeBoardForm() {
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writeBoardProcess(@Valid Board board, BindingResult result, SessionStatus status) {
        boardService.writeBoard(board);
        status.setComplete();
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/read/{seq}")
    public String readBoard(@PathVariable("seq") Long seq, Model model) {
        Board board = boardService.readBoard(seq);
        model.addAttribute("board", board);

        return "board/read/" + seq;
    }

    @RequestMapping(value = "/edit/{seq}")
    public String editBoardForm(@PathVariable("seq") Long seq) {
        return "board/edit/" + seq;
    }

    @RequestMapping(value = "/edit/{seq}", method = RequestMethod.POST)
    public String editBoardProcess(@PathVariable("seq") Long seq, @Valid Board board, BindingResult result, SessionStatus status) {
        boardService.editBoard(board);
        status.setComplete();
        return "redirect:/board/read/" + seq;
    }

    @RequestMapping(value = "/delete/{seq}")
    public String deleteBoard(@PathVariable("seq") Long seq) {
        boardService.deleteBoard(seq);
        return "redirect:/board/list";
    }
}
