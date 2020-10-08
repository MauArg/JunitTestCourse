package ar.com.casa.unittesting.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import ar.com.casa.unittesting.dao.ExternalInterface;

//Integration Test (unit test, no usando la infraestructura como haría un equipo de testing), 
//testeamos todas las capas incluyendo conexión a la BBDD.

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Lo agregamos para levantar el Application Context de
																// Spring. Escanea todas
// las clases en busca de componentes para ejecutar. El uso del Random Port
// sirve para evitar que muchos testeos se interfieran entre sí al querer usar
// un mismo puerto

//Podemos definir un archivo properties específico para este testeo (puede haber muchos)
@TestPropertySource(locations = { "classpath:test-configuration.properties" })
public class ItemControllerIT {

	// el restTemplate usará el puerto Random automáticamente
	@Autowired
	private TestRestTemplate restTemplate;

	// Este annotation serviría para emular un sistema externo del cual no queremos
	// depender en una prueba unitaria. Si quisieramos realmente usar la lógica de
	// esa interfaz externa (no recomendado) podríamos usar @Spy
	@MockBean
	private ExternalInterface externalInterface;

	// Este método levantará toda la applicacion como si se ejecutara el main en
	// UnitTestingApplication.java
	@Test
	public void contextLoads() throws JSONException {
		String response = this.restTemplate.getForEntity("/all-items-from-database", String.class).getBody().toString();
		JSONAssert.assertEquals(
				"[{id:10001,name:Item1,price:10},{id:10002,name:Item2},{id:10003,name:Item3,quantity:2}]", response,
				false);

		when(externalInterface.connect()).thenReturn("Se conectó, pero desde el Mock ;) ");
		assertEquals("Se conectó, pero desde el Mock ;) ", externalInterface.connect());
	}

}
