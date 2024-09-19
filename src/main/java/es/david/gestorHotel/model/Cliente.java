package es.david.gestorHotel.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@Column(name = "nombre",nullable = false)
	private String nombre;
	
	@Column(name = "apellidos",nullable = false)
	private String apellidos;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	@Column(name = "telefono",nullable = false)
	private int telefono;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Reserva> reservas;

}
