package es.david.gestorHotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.david.gestorHotel.dto.ReservaDto;
import es.david.gestorHotel.model.Reserva;
import es.david.gestorHotel.service.ClienteService;
import es.david.gestorHotel.service.HabitacionService;
import es.david.gestorHotel.service.ReservaService;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private HabitacionService habitacionService;

	@GetMapping
	public List<Reserva> obtenerReservas() {
		return reservaService.obtenerReservas();
	}

	@PostMapping("/crear")
	public ResponseEntity<Reserva> crearReserva(@RequestBody ReservaDto reservaDto) {
		try {
			Reserva reserva = reservaService.crearReserva(reservaDto);
			return ResponseEntity.ok(reserva);

		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PutMapping("/cancelar/{id}")
	public ResponseEntity<Reserva> cancelReserva(@PathVariable Long id) {
	    try {
	    	
	    	Reserva reserva = reservaService.buscarReserva(id);
	    	
	        // Directamente cancelamos la reserva. Si no existe, la excepción será lanzada desde cancelReserva()
	        reservaService.cancelReserva(id);
	        
	        // Retornar un mensaje indicando éxito
	        return ResponseEntity.ok(reserva);
	    } catch (NoSuchElementException e) {
	        // Si la reserva no es encontrada, retornamos un 404 con el mensaje de error
			return ResponseEntity.notFound().build();
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> buscarReserva(@PathVariable Long id){
	    try {
	        // Directamente cancelamos la reserva. Si no existe, la excepción será lanzada desde cancelReserva()
	        Reserva reserva = reservaService.buscarReserva(id);
	        
	        // Retornar un mensaje indicando éxito
	        return ResponseEntity.ok(reserva);
	    } catch (NoSuchElementException e) {
	        // Si la reserva no es encontrada, retornamos un 404 con el mensaje de error
			return ResponseEntity.notFound().build();
	    }
	}
	

}
