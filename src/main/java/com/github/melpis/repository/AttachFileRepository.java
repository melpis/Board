package com.github.melpis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.melpis.domain.AttachFile;


public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {
}
