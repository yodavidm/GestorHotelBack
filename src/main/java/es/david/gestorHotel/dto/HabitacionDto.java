package es.david.gestorHotel.dto;

import es.david.gestorHotel.model.Habitacion.EstadoHabitacion;
import es.david.gestorHotel.model.Habitacion.TipoHabitacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionDto {
	
	
	private TipoHabitacion tipo_habitacion;
	
	private int numero;
	
	private double precio;
	
	private EstadoHabitacion estado_hab;

}
