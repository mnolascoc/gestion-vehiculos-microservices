package com.coremei.usuario.service.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coremei.usuario.model.Moto;

@FeignClient(name = "moto-service", url = "http://localhost:8082")
@RequestMapping("/api/motos")
public interface MotoFeignClient {
	
	@PostMapping
	public Moto save(@RequestBody Moto moto);
	
	@GetMapping("/usuario/{usuarioId}")
	public List<Moto> getMotos(@PathVariable Integer usuarioId);
		
}