package es.david.gestorHotel.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.gestorHotel.dto.HabitacionDto;
import es.david.gestorHotel.model.Habitacion;
import es.david.gestorHotel.service.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {

	@Autowired
	private HabitacionService habitacionService;

	@GetMapping
	public List<Habitacion> obtenerHabitacions() {
		return habitacionService.obtenerHabitaciones();
	}

	@PostMapping("/crear")
	public ResponseEntity<Habitacion> crearHabitacion(@RequestBody HabitacionDto habitacionDto) {

		try {
			Habitacion habitacionCreada = habitacionService.crearHabitacion(habitacionDto);
			
			return ResponseEntity.ok(habitacionCreada);

		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@GetMapping("/habitacion/{id}")
	public ResponseEntity<Habitacion> buscarHabitacion(@PathVariable Long id){
		try {
			Habitacion habitacionEncontrada = habitacionService.buscarHabitacion(id);
			return ResponseEntity.ok(habitacionEncontrada);

		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Habitacion> editarHabitacion(@RequestBody HabitacionDto habitacionDto,@PathVariable Long id){
		
		try {
			Habitacion habitacionEditada = habitacionService.editarHabitacion(habitacionDto, id);
			
			return ResponseEntity.ok(habitacionEditada);

		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}


}
