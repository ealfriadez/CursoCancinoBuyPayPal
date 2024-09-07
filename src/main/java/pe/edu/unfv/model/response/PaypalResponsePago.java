package pe.edu.unfv.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.unfv.model.request.PaymentSource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaypalResponsePago {

	public String id;
	public String status;
	public PaymentSource payment_source;
	public List<PaypalLinks> links;
}
