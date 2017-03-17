package com.github.melpis.service;


import java.util.List;

import com.github.melpis.domain.AttachFile;
import org.springframework.web.multipart.MultipartFile;

import com.github.melpis.domain.Board;

import javax.servlet.http.HttpServletResponse;

public interface AttachFileService {

    void registerFileList(Board board, List<MultipartFile> attachFiles);

    void downloadFile(HttpServletResponse response, AttachFile fileInfo);

    void removeFile(List<AttachFile> fileInfo);

    AttachFile findOne(Long id);

}
