package es.david.gestorHotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {
	
	private String nombre;
	private String apellidos;
	private String email;
	private int telefono;

}
