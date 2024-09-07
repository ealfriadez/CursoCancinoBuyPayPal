package pe.edu.unfv.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.unfv.model.response.Amount;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseUnits {

	public String reference_id;
	public Amount amount;
}
