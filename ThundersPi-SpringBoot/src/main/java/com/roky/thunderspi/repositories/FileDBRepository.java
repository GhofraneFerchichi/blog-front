package com.roky.thunderspi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.roky.thunderspi.entities.FileDB;

;







public interface FileDBRepository extends JpaRepository<FileDB, Long> {
	

}
