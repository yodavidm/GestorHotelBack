package es.david.gestorHotel.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto {

	
	private LocalDate fecha_inicio;
	
	private LocalDate fecha_fin;
		
	private Long id_cliente;
	
	private Long id_habitacion;
}
