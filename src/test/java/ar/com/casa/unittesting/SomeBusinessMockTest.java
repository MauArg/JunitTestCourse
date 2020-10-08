package ar.com.casa.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.casa.unittesting.dao.ElDao;
import ar.com.casa.unittesting.service.MiServicio;

@ExtendWith(MockitoExtension.class)
class SomeBusinessMockTest {

	// Mockeamos el DAO para que devuelva lo que necesitemos (simulamos un DAO)
	@Mock
	ElDao daoMock;

	// Creamos el service
	@InjectMocks
	private MiServicio service;

	@BeforeEach
	public void before() {
		// Le seteamos el dao Mockeado
		service.setDao(daoMock);
	}

	@Test
	void setearIntDesdeDao() {
		// Mockeamos los valores del DAO a necesidad.
		when(daoMock.getInt()).thenReturn(12);

		// Ejecutamos el método que en teoría traería los datos desde el DAO
		service.setearUsandoDao();

		// Vemos si coincide con lo esperado
		assertEquals(12, service.getElObjeto().getUnEntero());
	}

	@Test
	void setearStringDesdeDao() {
		// Mockeamos los valores del DAO a necesidad.
		when(daoMock.getString()).thenReturn("String desde el Mock");

		// Ejecutamos el método que en teoría traería los datos desde el DAO
		service.setearUsandoDao();

		// Vemos si coincide con lo esperado
		assertEquals("String desde el Mock", service.getElObjeto().getUnaCadena());
	}

}
