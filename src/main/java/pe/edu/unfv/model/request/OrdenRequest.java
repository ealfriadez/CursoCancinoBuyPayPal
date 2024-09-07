package pe.edu.unfv.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdenRequest {

	public String intent;
	public List<PurchaseUnits> purchase_units;
	public PaymentSource payment_source;
}
