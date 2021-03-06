package com.coremei.usuario.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coremei.usuario.entity.Usuario;
import com.coremei.usuario.model.Auto;
import com.coremei.usuario.model.Moto;
import com.coremei.usuario.repository.UsuarioRepository;
import com.coremei.usuario.service.clients.AutoFeignClient;
import com.coremei.usuario.service.clients.MotoFeignClient;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	private RestTemplate restTemplate;
	private AutoFeignClient autoFeignClient;
	private MotoFeignClient motoFeignClient;

	public UsuarioService(UsuarioRepository usuarioRepository, RestTemplate restTemplate,
			AutoFeignClient autoFeignClient, MotoFeignClient motoFeignClient) {
		this.usuarioRepository = usuarioRepository;
		this.restTemplate = restTemplate;
		this.autoFeignClient = autoFeignClient;
		this.motoFeignClient = motoFeignClient;
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();
	}

	public Usuario getUsuarioById(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	public List<Auto> getAllAutos(Integer usuarioId) {
		return restTemplate.getForObject("http://localhost:8081/api/autos/usuario/" + usuarioId, List.class);
	}

	public List<Moto> getAllMotos(Integer usuarioId) {
		return restTemplate.getForObject("http://localhost:8081/api/motos/usuario/" + usuarioId, List.class);
	}
	
	public Auto saveAuto(Integer usuarioId, Auto auto) {
		auto.setUsuarioId(usuarioId);
		return autoFeignClient.save(auto);
	}
	
	public Moto saveMoto(Integer usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		return motoFeignClient.save(moto);
	}
	
	public Map<String,Object> getAllVehiculos(Integer usuarioId){
		Map<String,Object> vehiculos = new HashMap<>();
		
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if(usuario == null) {
			vehiculos.put("Mensaje", "Usuario no existe");
		}
		
		vehiculos.put("Usuario",usuario);
		List<Auto> autos = autoFeignClient.getAutos(usuarioId);
		if(autos.isEmpty()) {
			vehiculos.put("Autos","El usuario no tiene autos");
		}else {
			vehiculos.put("Autos", autos);
		}
		
		List<Moto> motos = motoFeignClient.getMotos(usuarioId);
		if(motos.isEmpty()) {
			vehiculos.put("Motos","El usuario no tiene motos");
		}else {
			vehiculos.put("Motos", motos);
		}
		
		return vehiculos;
	}

}