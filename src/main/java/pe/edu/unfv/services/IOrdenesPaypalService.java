package pe.edu.unfv.services;

import pe.edu.unfv.model.OrdenesPagoPaypalModel;

public interface IOrdenesPaypalService {

	OrdenesPagoPaypalModel savePaypalModel(OrdenesPagoPaypalModel model);
	OrdenesPagoPaypalModel buscarPorOrdenPaypalModel(String orden);
}
