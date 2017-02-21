package com.github.melpis.web;

import com.github.melpis.domain.Board;
import com.github.melpis.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/board")
public class BoardController {

    @Autowired
    BoardServiceImpl boardService;

    @RequestMapping(value = "/list")
    public String list(Model model,
                             @PageableDefault(sort={"seq"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<Board> boardListPage = boardService.findAll(pageable);
        model.addAttribute("boardListPage", boardListPage);

        return "board/list";
    }

    @RequestMapping(value = "/write")
    public String writeBoardForm() {
        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writeBoardProcess(@ModelAttribute("board") Board board) {
        boardService.writeBoard(board);

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/read/{seq}")
    public String readBoard(@PathVariable("seq") Long seq, Model model) {
        Board board = boardService.readBoard(seq);
        model.addAttribute("board", board);

        return "board/read/" + seq;
    }

    @RequestMapping(value = "/edit/{seq}")
    public String editBoardForm(@PathVariable("seq") Long seq, Model model) {
        Board editBoard = boardService.findOne(seq);
        model.addAttribute("editBoard", editBoard);

        return "board/edit/" + seq;
    }

    @RequestMapping(value = "/edit/{seq}", method = RequestMethod.POST)
    public String editBoardProcess(@ModelAttribute("board") Board board) {
        boardService.editBoard(board);

        return "redirect:/board/read/" + board.getSeq();
    }

    @RequestMapping(value = "/delete/{seq}")
    public String deleteBoard(@PathVariable("seq") Long seq) {
        boardService.deleteBoard(seq);

        return "redirect:/board/list";
    }
}
