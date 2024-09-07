package pe.edu.unfv.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import pe.edu.unfv.model.ExperienceContext;
import pe.edu.unfv.model.OrdenesPagoPaypalModel;
import pe.edu.unfv.model.request.OrdenRequest;
import pe.edu.unfv.model.request.PaymentSource;
import pe.edu.unfv.model.request.PurchaseUnits;
import pe.edu.unfv.model.response.Amount;
import pe.edu.unfv.model.response.Paypal;
import pe.edu.unfv.model.response.PaypalResponseLogin;
import pe.edu.unfv.model.response.PaypalResponsePago;
import pe.edu.unfv.services.implement.OrdenesPaypalServiceImpl;
import pe.edu.unfv.util.Constantes;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private OrdenesPaypalServiceImpl serviceImpl;	
	
	RestTemplate restTemplate = new RestTemplate();	
		
	public void BasicAuthClient(RestTemplateBuilder builder) {
		
		this.restTemplate = builder.build();
	}
	
	HttpHeaders createHeaders(String nombre, String password) {
		
		return new HttpHeaders() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

		{
			final String basicAuth = HttpHeaders.encodeBasicAuth(nombre, password, StandardCharsets.US_ASCII);
			setBasicAuth(basicAuth);
		}};
	}
	
	public String getToken() {
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, createHeaders(Constantes.PAYPAL_CLIENT_ID, Constantes.PAYPAL_CLIENT_SECRET));
		
		ResponseEntity<PaypalResponseLogin> responseEntity = this.restTemplate.exchange(
				Constantes.PAYPAL_BASE_URI + "/v1/oauth2/token", 
				HttpMethod.POST,
				requestEntity,
				PaypalResponseLogin.class
				);
		
		return responseEntity.getBody().access_token;
	}
	
	@GetMapping("")
	public String home(Model model) {
				
		String token = this.getToken();	
		
		//crear el orden de registro de paypal
		OrdenesPagoPaypalModel orden = this.serviceImpl.savePaypalModel(OrdenesPagoPaypalModel.builder()
				.token(token)
				.orden("")
				.nombre("")
				.correo("")
				.id_captura("")
				.monto("")
				.country_code("")
				.estado(0)
				.fecha(new Date())
				.paypal_request(token).build());
		
		//crear la orden de pago en paypal
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer ".concat(token));
		headers.set("Paypal-Request-Id", "orden_".concat(orden.getId().toString()));		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		List<PurchaseUnits> purchaseUnits = new ArrayList<>();
		purchaseUnits.add(new PurchaseUnits(
				"reference_".concat(orden.getId().toString()),
				new Amount("USD", "10.00")
				));
		
		OrdenRequest post = new OrdenRequest(
				"CAPTURE",
				purchaseUnits,
				new PaymentSource(
						new Paypal(
								new ExperienceContext(
										"IMMEDIATE_PAYMENT_REQUIRED",
										"PAYPAL",
										"elio@net",//sólo este valor es el que puedes cambiar
										"es-ES",
										"LOGIN",
										"SET_PROVIDED_ADDRESS",
										"PAY_NOW",
										"http://localhost:7010/curso_cancino_buypaypal/respuesta",
										"http://localhost:7010/curso_cancino_buypaypal/cancelado"
										)
								)
						)
				);

		
		HttpEntity<OrdenRequest> request = new HttpEntity<>(post, headers);		
		ResponseEntity<PaypalResponsePago> response = this.restTemplate.postForEntity(
				Constantes.PAYPAL_BASE_URI.concat("/v2/checkout/orders"),
				request,
				PaypalResponsePago.class);	
		
		//actualizo el valor de la orden de paypal
		orden.setOrden(response.getBody().id);
		this.serviceImpl.savePaypalModel(orden);
		
		System.out.println(response.getBody());
		model.addAttribute("token", token);
		model.addAttribute("response", response);
		
		return "home/home";
	}
	
}
