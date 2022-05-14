package com.coremei.moto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coremei.moto.entity.Moto;
import com.coremei.moto.repository.MotoRepository;

@Service
public class MotoService {
	
	private MotoRepository motoRepository;

	public MotoService(MotoRepository motoRepository) {
		this.motoRepository = motoRepository;
	}
	
	public List<Moto> getAll(){
		return motoRepository.findAll();
	}
	
	public Moto getMotoById(Integer id) {
		return motoRepository.findById(id).orElse(null);
	}
	
	public List<Moto> getMotoByUsuario(Integer usuarioId) {
		return motoRepository.findByUsuarioId(usuarioId);
	}
	
	public Moto save(Moto moto) {
		return motoRepository.save(moto);
	}
	
}
