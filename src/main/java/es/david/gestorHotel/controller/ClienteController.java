package es.david.gestorHotel.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.david.gestorHotel.dto.ClienteDto;
import es.david.gestorHotel.model.Cliente;
import es.david.gestorHotel.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping()
	public List<Cliente> obtenerClientes() {
		return clienteService.obtenerClientes();
	}

	@PostMapping("/crear")
	public ResponseEntity<Cliente> crearCliente(@RequestBody ClienteDto clienteDto) {
		try {
			Cliente cliente = clienteService.crearCliente(clienteDto);
			return ResponseEntity.ok(cliente);

		} catch (NoSuchElementException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	@PutMapping("editar/{id}")
	public ResponseEntity<Cliente> editarCliente(@RequestBody ClienteDto clienteDto, @PathVariable Long id) {
		try {
			Cliente clienteActualizado = clienteService.editarCliente(clienteDto, id);
			return ResponseEntity.ok(clienteActualizado);

		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
		Optional<Cliente> clienteEncontrado = clienteService.buscarPorId(id);

		if (clienteEncontrado.isPresent()) {
			return ResponseEntity.ok(clienteEncontrado.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
