package com.coremei.auto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coremei.auto.entity.Auto;
import com.coremei.auto.repository.AutoRepository;

@Service
public class AutoService {
	
	private AutoRepository autoRepository;

	public AutoService(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}
	
	public List<Auto> getAll(){
		return autoRepository.findAll();
	}
	
	public Auto getAutoById(Integer id) {
		return autoRepository.findById(id).orElse(null);
	}
	
	public List<Auto> getAutoByUsuario(Integer usuarioId) {
		return autoRepository.findByUsuarioId(usuarioId);
	}
	
	public Auto save(Auto auto) {
		return autoRepository.save(auto);
	}
	
}