package es.david.gestorHotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.gestorHotel.dto.ReservaDto;
import es.david.gestorHotel.model.Cliente;
import es.david.gestorHotel.model.Habitacion;
import es.david.gestorHotel.model.Reserva;
import es.david.gestorHotel.repository.ClienteRepository;
import es.david.gestorHotel.repository.HabitacionRepository;
import es.david.gestorHotel.repository.ReservaRepository;

@Service
public class ReservaService {
	
	Logger logger = LoggerFactory.getLogger(ReservaService.class);

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HabitacionRepository habitacionRepository;

	public List<Reserva> obtenerReservas() {
		return reservaRepository.findAll();
	}

	// mapear reservaDto a objeto Reserva
	public Reserva crearReserva(ReservaDto reservaDto) {

        // Validar si el cliente existe
        Cliente cliente = clienteRepository.findById(reservaDto.getId_cliente())
                .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con id: " + reservaDto.getId_cliente()));

        // Validar si la habitación existe
        Habitacion habitacion = habitacionRepository.findById(reservaDto.getId_habitacion())
                .orElseThrow(() -> new NoSuchElementException("Habitación no encontrada con id: " + reservaDto.getId_habitacion()));

        // Validar que la fecha de fin sea posterior a la fecha de inicio
        if (reservaDto.getFecha_fin().isBefore(reservaDto.getFecha_inicio())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        
        boolean ocupada = reservaRepository.existsByHabitacionAndFechas(habitacion.getId_habitacion(), reservaDto.getFecha_inicio(), reservaDto.getFecha_fin());
        
        if(ocupada) {
        	logger.info("La habitacion " + habitacion.getId_habitacion() + " esta ocupada los dias: " + reservaDto.getFecha_inicio() + " hasta " + reservaDto.getFecha_fin());
            throw new IllegalArgumentException("Esta ocupada");

        }
        
    	logger.info("La habitacion " + habitacion.getId_habitacion() + " esta libre los dias: " + reservaDto.getFecha_inicio() + " hasta " + reservaDto.getFecha_fin());

        
        
        //actualizamos estado de habitacion al crear la reserva
        habitacion.setEstado_hab("Reservada");
        habitacionRepository.save(habitacion);

        // Crear la reserva usando el builder
        Reserva reserva = Reserva.builder()
                .fecha_inicio(reservaDto.getFecha_inicio())
                .fecha_fin(reservaDto.getFecha_fin())
                .estado_reserva("Reservada")
                .cliente(cliente)
                .habitacion(habitacion)
                .build();

        // Guardar la reserva en la base de datos
        return reservaRepository.save(reserva);
    }
	
	public Reserva cancelReserva(Long id) {
	    // Buscar la reserva por ID o lanzar una excepción personalizada si no se encuentra
	    Reserva reserva = reservaRepository.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Reserva no encontrada con id: " + id));

	    // Registrar la acción de cancelación (si se desea, opcional)
	    logger.info("Cancelando la reserva con id: " + id + reserva.getHabitacion().getId_habitacion());

	    // Eliminar la reserva
	    
	    reserva.setEstado_reserva("Cancelada");
	    reservaRepository.save(reserva);
	    
	    return reserva;
	    
	}
	
	public Reserva buscarReserva(Long id){
		Reserva reservaEncontrada = reservaRepository.findById(id)
				.orElseThrow(()-> new NoSuchElementException("Reserva no encontrada con id: " + id));
		
		return reservaEncontrada;
	}
	
	public boolean existeReservaHabitacion(Long id_habitacion,LocalDate fechaInicio,LocalDate fechaFin) {
		return reservaRepository.existsByHabitacionAndFechas(id_habitacion, fechaInicio, fechaFin);
	}
	

}
