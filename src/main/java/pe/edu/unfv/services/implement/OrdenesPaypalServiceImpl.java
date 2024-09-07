package pe.edu.unfv.services.implement;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pe.edu.unfv.model.OrdenesPagoPaypalModel;
import pe.edu.unfv.repository.IOrdenesPaypalRepository;
import pe.edu.unfv.services.IOrdenesPaypalService;

@Service
@Primary
@AllArgsConstructor
public class OrdenesPaypalServiceImpl implements IOrdenesPaypalService{

	private IOrdenesPaypalRepository repository;
	
	@Override
	public OrdenesPagoPaypalModel savePaypalModel(OrdenesPagoPaypalModel model) {
		
		return this.repository.save(model);
	}

	@Override
	public OrdenesPagoPaypalModel buscarPorOrdenPaypalModel(String orden) {
		
		return this.repository.findByOrden(orden);
	}
}
