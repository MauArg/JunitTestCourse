package ar.com.casa.unittesting.dao;

import org.springframework.stereotype.Component;

@Component
public class ExternalInterface {
	// This class pretends to be an external interface that interacts with other
	// external systems.
	public String connect() {
		return "Connected to something!";
	}

}
