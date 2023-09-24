package com.github.szabogabriel.minidashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.szabogabriel.minidashboard.data.entites.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {

}
