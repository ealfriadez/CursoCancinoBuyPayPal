package pe.edu.unfv.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.unfv.model.response.Paypal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSource {

	public Paypal paypal;
}
