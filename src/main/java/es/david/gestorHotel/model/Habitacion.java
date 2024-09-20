package es.david.gestorHotel.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Habitacion")
public class Habitacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_habitacion;
	
	@Column(name = "tipo_habitacion",nullable = false)
	private TipoHabitacion tipo_habitacion;
	
	@Column(name = "numero",nullable = false,unique = true)
	private int numero;
	
	@Column(name = "precio",nullable = false)
	private double precio;
	
	@Column(name = "estado_hab",nullable = false)
	private EstadoHabitacion estado_hab;
	
    public enum TipoHabitacion {
        DOBLE, SINGLE, VIP
    }
    
    public enum EstadoHabitacion {
        LIBRE, OCUPADA, MANTENIMIENTO
    }

}
