package com.coremei.moto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coremei.moto.entity.Moto;
import com.coremei.moto.service.MotoService;

@RestController
@RequestMapping("/api/motos")
public class MotoController {
	
	private MotoService motoService;

	public MotoController(MotoService motoService) {
		this.motoService = motoService;
	}

	@GetMapping
	public ResponseEntity<List<Moto>> getAll() {
		List<Moto> Motos = motoService.getAll();
		if (Motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(Motos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> getMoto(@PathVariable("id") Integer id) {
		Moto Moto = motoService.getMotoById(id);
		if (Moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Moto);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Moto>> getMotoByUsuario(@PathVariable("id") Integer usuarioId) {
		List<Moto> Moto = motoService.getMotoByUsuario(usuarioId);
		if (Moto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(Moto);
	}

	@PostMapping
	public ResponseEntity<Moto> createUsuario(@RequestBody Moto Moto) {
		Moto nuevoMoto = motoService.save(Moto);
		return ResponseEntity.ok(nuevoMoto);
	}

}
