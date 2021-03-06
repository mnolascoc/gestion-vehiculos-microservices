package com.coremei.auto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coremei.auto.entity.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer> {
	
	List<Auto> findByUsuarioId(Integer usuarioId);
}
