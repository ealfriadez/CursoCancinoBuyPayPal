package pe.edu.unfv.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaypalResponseLogin {

	public String scope;
	public String access_token;
	public String token_type;
	public String app_id;
	public String expires_in;
	public String nonce;
}
