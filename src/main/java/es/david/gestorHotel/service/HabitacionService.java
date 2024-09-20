package es.david.gestorHotel.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.gestorHotel.dto.HabitacionDto;
import es.david.gestorHotel.model.Habitacion;
import es.david.gestorHotel.model.Habitacion.EstadoHabitacion;
import es.david.gestorHotel.repository.HabitacionRepository;

@Service
public class HabitacionService {

	private static final Logger logger = LoggerFactory.getLogger(HabitacionService.class);

	@Autowired
	private HabitacionRepository habitacionRepository;

	public List<Habitacion> obtenerHabitaciones() {
		return habitacionRepository.findAll();
	}

	public Habitacion crearHabitacion(HabitacionDto habitacionDto) {

		Habitacion habitacion = Habitacion.builder()
				.tipo_habitacion(habitacionDto.getTipo_habitacion())
				.numero(habitacionDto.getNumero())
				.precio(habitacionDto.getPrecio())
				.estado_hab(EstadoHabitacion.LIBRE)
				.build();

		return habitacion;
	}

	public Habitacion buscarHabitacion(Long id) {

		Habitacion habitacionEncontrada = validarHabitacion(id);

		return habitacionEncontrada;
	}
	
	public void eliminarHabitacion(Long id) {
	    Habitacion habitacionEliminar = validarHabitacion(id);
	    try {
	        habitacionRepository.delete(habitacionEliminar);
	        logger.info("Habitacion con id " + id + " eliminada correctamente.");
	    } catch (Exception e) {
	        logger.error("Error al eliminar la habitacion con id " + id, e);
	        throw new IllegalStateException("No se pudo eliminar la habitacion con id " + id + " debido a restricciones", e);
	    }
	}
	
	public Habitacion editarHabitacion(HabitacionDto habitacionDto, Long id) {
	    Habitacion habitacionEditar = validarHabitacion(id);

	    if (habitacionDto.getTipo_habitacion() != null) {
	        habitacionEditar.setTipo_habitacion(habitacionDto.getTipo_habitacion());
	    }
	    if (habitacionDto.getNumero() != 0) { // Asumiendo que 0 no es un número válido para habitaciones
	        habitacionEditar.setNumero(habitacionDto.getNumero());
	    }
	    if (habitacionDto.getPrecio() > 0) {
	        habitacionEditar.setPrecio(habitacionDto.getPrecio());
	    }
	    if (habitacionDto.getEstado_hab() != null) {
	        habitacionEditar.setEstado_hab(habitacionDto.getEstado_hab());
	    }

	    return habitacionRepository.save(habitacionEditar);
	}



	// validaciones auxiliares
	public Habitacion validarHabitacion(Long id) {
		Habitacion habitacionEncontrada = habitacionRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Habitacion con id " + id + " no encontrada"));

		return habitacionEncontrada;
	}

}
