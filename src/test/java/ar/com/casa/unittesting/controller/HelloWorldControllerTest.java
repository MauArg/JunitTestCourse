package ar.com.casa.unittesting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.apache.tomcat.jni.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class) //Not needed when using Junit5

@WebMvcTest(HelloWorldController.class)
public class HelloWorldControllerTest {

	// Mock MVC nos provee los métodos para crear los requests
	@Autowired // Inyectado por Spring
	private MockMvc mockMvc;

	@Test
	public void helloWorldBasicTest() throws Exception {
		// Simple testeo, solamente llamamos a /hello-world y ver que devuelva "Hello
		// World"
		// No se puede llamar al método, porque no es una clase Java, en vez de eso
		// necesitamos llamar a un path
		RequestBuilder request = MockMvcRequestBuilders.get("/hello-world").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request);

		// Obtenemos la respuesta y la guardamos
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().string("Hello World")).andReturn();

		// Una vez hecho el request, verificamos la respuesta (no necesario, se hace
		// arriba con MockMvc)
		// Si los chequeos se hacen muy complejos entonces sí es recomendable hacer un
		// assertEquals como está abajo
		// assertEquals("Hello World", result.getResponse().getContentAsString());

	}

}
