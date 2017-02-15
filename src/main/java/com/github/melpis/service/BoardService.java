package com.github.melpis.service;

import com.github.melpis.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hongsung-won on 2017. 2. 15..
 */
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
}
