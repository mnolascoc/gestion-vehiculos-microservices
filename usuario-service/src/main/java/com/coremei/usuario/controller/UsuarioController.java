package com.coremei.usuario.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coremei.usuario.entity.Usuario;
import com.coremei.usuario.model.Auto;
import com.coremei.usuario.model.Moto;
import com.coremei.usuario.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable("id") Integer id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario = usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	
	@GetMapping("/autos/{usuarioId}")
	public ResponseEntity<List<Auto>> getAutos(@PathVariable("usuarioId") Integer usuarioId){
		System.out.println("Usuario id: " + usuarioId);
		
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		
		if(usuario == null) {
			System.out.println("no encontrado");
			return ResponseEntity.notFound().build();
		}
		List<Auto> autosUsuario = usuarioService.getAllAutos(usuarioId);
		return ResponseEntity.ok(autosUsuario);
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") Integer usuarioId){
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		List<Moto> motosUsuario = usuarioService.getAllMotos(usuarioId);
		return ResponseEntity.ok(motosUsuario);
	}
	
	@PostMapping("/autos/{usuarioId}")
	public ResponseEntity<Auto> saveAuto(@PathVariable Integer usuarioId, @RequestBody Auto auto){
		Auto nuevoAuto = usuarioService.saveAuto(usuarioId, auto);
		return ResponseEntity.ok(nuevoAuto);
	}
	
	@PostMapping("/motos/{usuarioId}")
	public ResponseEntity<Moto> saveMoto(@PathVariable Integer usuarioId, @RequestBody Moto moto){
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	@GetMapping("/vehiculos/{usuarioId}")
	public ResponseEntity<Map<String,Object>> getAllVehiculos(@PathVariable Integer usuarioId){
		return ResponseEntity.ok(usuarioService.getAllVehiculos(usuarioId));
	}
	
}