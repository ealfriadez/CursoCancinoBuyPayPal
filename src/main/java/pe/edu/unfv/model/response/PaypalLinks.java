package pe.edu.unfv.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaypalLinks {

	public String href;
	public String rel;
	public String method;
}
