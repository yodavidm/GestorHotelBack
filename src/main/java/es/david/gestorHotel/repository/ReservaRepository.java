package es.david.gestorHotel.repository;


import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.david.gestorHotel.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Reserva r " +
            "WHERE r.habitacion.id_habitacion = :idHabitacion " +
            "AND ((:fechaInicio <= r.fecha_fin AND :fechaFin >= r.fecha_inicio))")
     boolean existsByHabitacionAndFechas(@Param("idHabitacion") Long idHabitacion,
                                         @Param("fechaInicio") LocalDate fechaInicio,
                                         @Param("fechaFin") LocalDate fechaFin);	

}
