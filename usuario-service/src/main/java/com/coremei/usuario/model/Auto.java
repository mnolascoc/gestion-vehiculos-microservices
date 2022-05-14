package com.coremei.usuario.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Auto {
	
	private String marca;
	private String modelo;
	private Integer usuarioId;
	
}