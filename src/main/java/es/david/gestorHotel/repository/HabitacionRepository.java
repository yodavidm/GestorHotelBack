package es.david.gestorHotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.david.gestorHotel.model.Habitacion;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long>{

}
