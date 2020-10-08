package ar.com.casa.unittesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.casa.unittesting.model.Item;
import ar.com.casa.unittesting.service.ItemBusinessService;

@RestController
public class ItemController {

	@Autowired
	private ItemBusinessService businessService;

	// Un controller que devuelve un objeto en JSon
	@GetMapping("/dummy-item")
	public Item dummyItem() {
		return new Item(1, "Ball", 10, 100);
	}

	@GetMapping("/item-from-business-service")
	public Item itemFromBusinessService() {
		return businessService.retrieveHardcodedItem();
	}

	@GetMapping("/all-items-from-database")
	public List<Item> retrieveAllItems() {
		return businessService.retrieveAllItems();
	}

}
