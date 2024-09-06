package pe.edu.unfv.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordenes_paypal")
public class OrdenesPagoPaypal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public String token;
	public String orden;
	public String nombre;
	public String correo;
	public String id_captura;
	public String monto;
	public String country_code;
	public int estado;
	public Date fecha;
	public String paypal_request;
	
	public OrdenesPagoPaypal(String token, String orden, String nombre, String correo, String id_captura, String monto,
			String country_code, int estado, Date fecha, String paypal_request) {
		super();
		this.token = token;
		this.orden = orden;
		this.nombre = nombre;
		this.correo = correo;
		this.id_captura = id_captura;
		this.monto = monto;
		this.country_code = country_code;
		this.estado = estado;
		this.fecha = fecha;
		this.paypal_request = paypal_request;
	}
}
