package es.david.gestorHotel.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.david.gestorHotel.dto.ClienteDto;
import es.david.gestorHotel.model.Cliente;
import es.david.gestorHotel.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private ClienteRepository clienteRepository;

	// obtenemos todos los clientes
	public List<Cliente> obtenerClientes() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	// creamos un cliente mappeandolo a partir de su Dto
	public Cliente crearCliente(ClienteDto clienteDto) {

		Cliente clienteMapped = Cliente.builder().nombre(clienteDto.getNombre()).apellidos(clienteDto.getApellidos())
				.email(clienteDto.getEmail()).telefono(clienteDto.getTelefono()).build();

		logger.info("El cliente " + clienteMapped.getNombre() + " " + clienteMapped.getApellidos() + " ha sido creado"); //cambiarlo en el controller

		return clienteRepository.save(clienteMapped);
	}

	public void eliminarCliente(Long id) {

		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

		// comprobamos existencia de cliente
		if (clienteEncontrado.isEmpty()) {
			throw new NoSuchElementException("Cliente no encontrado con id: " + id);
		} else {

			// rellenamos cliente
			Cliente cliente = clienteEncontrado.get();
			logger.info("El cliente " + cliente.getNombre() + " " + cliente.getApellidos() + " ha sido creado");

			clienteRepository.deleteById(id);
		}
	}

	// para editar un cliente, necesitamos buscar su "id" y un objeto "clienteDto"
	// para reconstruirlo
	public Cliente editarCliente(ClienteDto clienteDto, Long id) {

		Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);

		if (clienteEncontrado.isEmpty()) {
			throw new NoSuchElementException("Cliente no encontrado con id: " + id);
		} else {
			Cliente cliente = clienteEncontrado.get();

			cliente.setNombre(clienteDto.getNombre());
			cliente.setApellidos(clienteDto.getApellidos());
			cliente.setEmail(clienteDto.getEmail());
			cliente.setTelefono(clienteDto.getTelefono());

			logger.info("El cliente " + cliente.getNombre() + " " + cliente.getApellidos() + " ha sido actualizado");

			return clienteRepository.save(cliente);
		}
	}

	// para pruebas
	public void eliminarTodos() {
		clienteRepository.deleteAll();
	}

}
