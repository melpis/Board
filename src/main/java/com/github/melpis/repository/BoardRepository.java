package com.github.melpis.repository;

import com.github.melpis.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    EntityManager em;

    public List<Board> list() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    /*
    public List<Board> list(int page) {

    }
    */

    public void insertBoard(Board board) {
        board.setDate(new Date());
        em.persist(board);
    }

    public Board getBoard(Long seq) {
        return em.find(Board.class, seq);
    }

    public void editBoard(Board board) {

    }

    public void deleteBoard(Long seq) {
        em.remove(seq);
    }
}
