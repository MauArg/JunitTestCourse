package ar.com.casa.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.casa.unittesting.dao.ItemRepository;
import ar.com.casa.unittesting.model.Item;
import ar.com.casa.unittesting.service.ItemBusinessService;

//Testeo de la capa de Servicio emulando el DAO

//@SpringBootTest // Con este annotation también funciona, pero levanta todo el Application Context innecesariamente (solo necesitamos testear la lógica de esta clase)

@ExtendWith(MockitoExtension.class)
class ItemBusinessServiceTest {

	// Mockeamos el DAO para que devuelva lo que necesitemos (simulamos un DAO)
	@Mock
	private ItemRepository daoMock;

	// Creamos el service
	@InjectMocks
	private ItemBusinessService service;

	@Test
	void businessLogicTest() {
		// Mockeamos los valores del DAO a necesidad.
		when(daoMock.findAll()).thenReturn(Arrays.asList(new Item(2, "Item2", 10, 100), new Item(3, "Item3", 20, 20)));

		// Ejecutamos el método que en teoría traería los datos desde el DAO
		List<Item> items = service.retrieveAllItems();

		// Revisamos si la lógica del service funciona (la que calcula 'value')
		assertEquals(1000, items.get(0).getValue());
		assertEquals(400, items.get(1).getValue());
	}

}
