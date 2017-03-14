package com.github.melpis.service;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.github.melpis.domain.Board;

public interface AttachFileService {

    void registerFileList(Board board, List<MultipartFile> attachFiles);

}
