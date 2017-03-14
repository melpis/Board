package com.github.melpis.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.melpis.domain.AttachFile;
import com.github.melpis.domain.Board;
import com.github.melpis.repository.AttachFileRepository;

@Service
@Transactional
public class AttachFileServiceImpl implements AttachFileService {

    @Autowired
    AttachFileRepository attachFileRepository;

    public void registerFileList(Board board, List<MultipartFile> attachFiles) {
        String filePath = getFilePath(board.getId().toString());

        for(int i=0 ; i < attachFiles.size() ; i++) {
            uploadFile(filePath, attachFiles.get(i), board);
        }
    }

    public void uploadFile(String filePath, MultipartFile file, Board board) {

        String fileName = file.getOriginalFilename();

        // 서버에 파일 저장
        File uploadFile = new File(filePath + "/"+ fileName);
        BufferedOutputStream outputStream = null;
        try {
            byte[] bytes = file.getBytes();
            outputStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        AttachFile attachFile = new AttachFile();
        attachFile.setFileName(fileName);
        attachFile.setFilePath(filePath);
        attachFile.setFileSize(file.getSize());
        attachFile.setBoard(board);
        attachFileRepository.save(attachFile);
    }

    public String getFilePath(String boardId) {
        File filePath = new File("D:/upload/" + boardId);

        if (!filePath.exists()) {
            filePath.mkdirs();
        } else {
            File[] files = filePath.listFiles();
            for(File file : files) {
                filePath.delete();
            }
        }

        return filePath.getName();
    }
}
