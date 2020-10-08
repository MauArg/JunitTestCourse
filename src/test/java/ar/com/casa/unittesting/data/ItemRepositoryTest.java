package ar.com.casa.unittesting.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ar.com.casa.unittesting.dao.ItemRepository;
import ar.com.casa.unittesting.model.Item;

@DataJpaTest // SpringBoot provee esta clase para probar repositorias JPA. Esto creará un
				// datasource a la BBDD (en este caso es una BBDD 'In Memory' H2)
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository dao;

	@Test
	public void testFindAll() {
		List<Item> items = dao.findAll();
		assertEquals(3, items.size());
	}

	@Test
	public void testFindById() {
		Item itemToFind = new Item(10001, null, 0, 0);
		Optional<Item> returnedItemOptional = dao.findById(10001);
		Item returnedItem = returnedItemOptional.orElse(new Item(1, "Empty", 0, 0));
		assertEquals(new Item(10001, "Item1", 10, 20), returnedItem);
	}

}
