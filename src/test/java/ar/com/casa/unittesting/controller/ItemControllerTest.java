package ar.com.casa.unittesting.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ar.com.casa.unittesting.model.Item;
import ar.com.casa.unittesting.service.ItemBusinessService;

//@RunWith(SpringRunner.class) //Not needed when using Junit5

//Testeo del controller emulando la capa de servicio
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

	// Mock MVC nos provee los métodos para crear los requests
	@Autowired // Inyectado por Spring
	private MockMvc mockMvc;

	// Si intentamos correr esto sin nada más, falla porque no encuentra el objeto
	// de la capa de servicio "ItemBusinessService". Esto se debe a que en el test
	// se carga sólo la lógica del controlador (por eso se especifica la clase a
	// mockear en la definicion de la clase del test
	// @WebMvcTest(ItemController.class))
	@MockBean
	ItemBusinessService service; // Este mock va a ser 'null' por default (es un objeto). Necesitamos mockear el
									// comportamiento y para eso se usa el when()

	@Test
	public void dummyItemBasicTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/dummy-item").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request);
		// NOTA: Para hacer otros tipos de request como POST, hay que cambiar a
		// .post(/URL) y agregar .content(JSON) para mandar el body del request y
		// .contentType(MediaType) para definir el tipo de contenido

		// Obtenemos la respuesta y la guardamos, usamos el método .json() para
		// verificar si el contenido del json coincide con lo devuelto
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"id\": 1,\"name\":\"Ball\",\"price\":10,\"quantity\":100}")).andReturn();
		// Para el resultado podemos chequear varios tipos de estados (203, 404, 500,
		// etc) y también revisar si el header contiene alguna cadena en particular

		// JSONAssert.assertEquals(expected, actual, strict);
	}

	@Test
	public void itemFromBusinessServiceBasicTest() throws Exception {
		// Con este when estamos emulando el comportamiento de la capa de servicio
		when(service.retrieveHardcodedItem()).thenReturn(new Item(2, "Item2", 10, 100));

		RequestBuilder request = MockMvcRequestBuilders.get("/item-from-business-service")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request);

		// Obtenemos la respuesta y la guardamos, usamos el método .json() para
		// verificar si el contenido del json coincide con lo devuelto
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{id: 2,name:Item2,price:10,quantity:100}")).andReturn();

		// JSONAssert.assertEquals(expected, actual, strict);
	}

	@Test
	public void retrieveAllItemsBasicTest() throws Exception {
		// Con este when estamos emulando el comportamiento de la capa de servicio. En
		// este caso devolvemos una lista
		when(service.retrieveAllItems())
				.thenReturn(Arrays.asList(new Item(2, "Item2", 10, 100), new Item(3, "Item3", 20, 20)));

		RequestBuilder request = MockMvcRequestBuilders.get("/all-items-from-database")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request);

		// Obtenemos la respuesta y la guardamos, usamos el método .json() para
		// verificar si el contenido del json coincide con lo devuelto
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content()
						.json("[{id: 2,name:Item2,price:10,quantity:100},{id: 3,name:Item3,price:20,quantity:20}]"))
				.andReturn();

		// Se usa JSonAssert library para efectuar las comparaciones. El campo 'strict'
		// define si el JSon debería estar completo o no y por defecto es false
		// JSONAssert.assertEquals(expected, actual, strict);
	}

}
