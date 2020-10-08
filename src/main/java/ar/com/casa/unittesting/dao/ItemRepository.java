package ar.com.casa.unittesting.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.casa.unittesting.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
