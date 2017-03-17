package com.github.melpis.service;

import java.io.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.github.melpis.domain.AttachFile;
import com.github.melpis.domain.Board;
import com.github.melpis.repository.AttachFileRepository;

import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
public class AttachFileServiceImpl implements AttachFileService {

    @Autowired
    AttachFileRepository attachFileRepository;

    @Override
    public AttachFile findOne(Long id) {
        return attachFileRepository.findOne(id);
    }

    @Override
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

    @Override
    public void downloadFile(HttpServletResponse response, AttachFile fileInfo) {
        response.setContentType(fileInfo.getContentType() + "; charset=" + "EUC-KR");
        response.setHeader("Content-Length", "" + fileInfo.getFileSize());
        try {
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileInfo.getFileName().getBytes("EUC-KR"), "latin1") + ";");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File downFile = new File(fileInfo.getFilePath(), fileInfo.getFileName());
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            fis = new FileInputStream(downFile);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(response.getOutputStream());

            int readCount = 0;
            byte[] buffer = new byte[4096];

            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }
            bos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFile(List<AttachFile> filesInfo) {
        for (int i = 0; i < filesInfo.size() ; i++) {
            File file = new File(filesInfo.get(i).getFilePath(), filesInfo.get(i).getFileName());
            file.delete();
        }
    }
}
