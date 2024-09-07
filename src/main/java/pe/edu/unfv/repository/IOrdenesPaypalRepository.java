package pe.edu.unfv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.unfv.model.OrdenesPagoPaypalModel;

public interface IOrdenesPaypalRepository extends JpaRepository<OrdenesPagoPaypalModel, Integer>{

	OrdenesPagoPaypalModel findByOrden(String orden);
}
