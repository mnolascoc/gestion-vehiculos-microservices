package com.coremei.auto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coremei.auto.entity.Auto;
import com.coremei.auto.service.AutoService;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

	private AutoService autoService;

	public AutoController(AutoService autoService) {
		this.autoService = autoService;
	}

	@GetMapping
	public ResponseEntity<List<Auto>> getAll() {
		List<Auto> autos = autoService.getAll();
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Auto> getAuto(@PathVariable("id") Integer id) {
		Auto auto = autoService.getAutoById(id);
		if (auto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(auto);
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Auto>> getAutoByUsuario(@PathVariable("id") Integer usuarioId) {
		List<Auto> auto = autoService.getAutoByUsuario(usuarioId);
		if (auto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(auto);
	}

	@PostMapping
	public ResponseEntity<Auto> createUsuario(@RequestBody Auto auto) {
		Auto nuevoAuto = autoService.save(auto);
		return ResponseEntity.ok(nuevoAuto);
	}

}