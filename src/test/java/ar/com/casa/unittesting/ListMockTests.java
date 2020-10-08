package ar.com.casa.unittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

//Más info de Mockito https://github.com/mockito/mockito/wiki/FAQ

public class ListMockTests {

	List<String> mockList = mock(List.class);

	@Test
	public void basicSizeTest() {
		when(mockList.size()).thenReturn(5);
		assertEquals(5, mockList.size());
	}

	@Test
	public void returnDifferentValues() {
		when(mockList.size()).thenReturn(12).thenReturn(15);
		assertEquals(12, mockList.size());
		assertEquals(15, mockList.size());
	}

	@Test
	public void returnWithParametes() {
		when(mockList.get(1)).thenReturn("Hola");
		assertEquals("Hola", mockList.get(1));
	}

	@Test
	public void returnWithGenericParametes() {
		when(mockList.get(anyInt())).thenReturn("Hola");
		assertEquals("Hola", mockList.get(5));
	}

	@Test
	public void methodVerification() {
		String value1 = mockList.get(0);
		String value2 = mockList.get(0);

//		verify(mockList).get(anyInt());		//Por defecto, chequea que sea invocado 1 sola vez
		verify(mockList, times(2)).get(anyInt());
		verify(mockList, atLeast(1)).get(anyInt());
		verify(mockList, atLeastOnce()).get(anyInt());
		verify(mockList, atMost(2)).get(anyInt());
		verify(mockList, never()).get(2);
	}

	@Test
	public void argumentCapturing() {
		mockList.add("Un valor mock");

		// Verification, se captura el valor usando un ArgumentCaptor
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList).add(captor.capture());

		// Recuperamos el valor capturado por el captor
		assertEquals("Un valor mock", captor.getValue());
	}

	@Test
	public void multipleArgumentCapturing() {
		mockList.add("Un valor mock");
		mockList.add("Otro valor mock");

		// Verification, se captura el valor usando un ArgumentCaptor
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mockList, times(2)).add(captor.capture());

		// Recuperamos todos los valores capturados por el captor
		List<String> allValues = captor.getAllValues();
		assertEquals("Un valor mock", allValues.get(0));
		assertEquals("Otro valor mock", allValues.get(1));
	}

	@Test
	public void returnMultipleValues() {
		when(mockList.size()).thenReturn(2);
		when(mockList.get(0)).thenReturn("Primer elemento");
		when(mockList.get(1)).thenReturn("Segundo elemento");
		for (int i = 0; i < mockList.size(); i++) {
//			System.out.println(mockList.get(i));
		}
	}

	// Uso de Spy. Utiliza la lógica de la clase original en lugar de un mock
	// "vacío". Se puede redefinir comportamientos igualmente
	@Test
	public void mocking() { // Imaginemos que tenemos este mock
		System.out.println("---Usando mock vacío---");
		ArrayList arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0)); // retorna Null (valor por defecto)
		System.out.println(arrayListMock.size()); // 0 (defecto)
		arrayListMock.add("Un valor"); // No hace nada porque es un mock "vacío" sin lógica
		arrayListMock.add("Otro valor");
		System.out.println(arrayListMock.size()); // sigue siendo 0 (mock sin lógica original!)
		when(arrayListMock.size()).thenReturn(5); // redefinimos el comportamiento
		System.out.println(arrayListMock.size()); // retorna 5 (comportamiento redefinido)
	}

	@Test
	public void spying() { // Usamos un Spy para mantener lógica original
		System.out.println("---Usando Spy---");
		ArrayList arrayListSpy = spy(ArrayList.class);

		arrayListSpy.add("Valor0");

		System.out.println(arrayListSpy.get(0)); // retorna el valor agregado (como si fuera la clase original)
		System.out.println(arrayListSpy.size()); // retorna 1 (hay un elemento)
		arrayListSpy.add("Un valor"); // Sigue agregando elementos
		arrayListSpy.add("Otro valor");
		System.out.println(arrayListSpy.size()); // devuelve el valor real de la lista (lógica original)
		when(arrayListSpy.size()).thenReturn(5); // redefinimos el comportamiento
		System.out.println(arrayListSpy.size()); // retorna 5 (comportamiento redefinido). En este punto, el
													// comportamiento original se pierde y permanece solo el redefinido
													// (será siempre 5)

		// Con spy podemos revisar su comportamiento y ver que valores maneja
		verify(arrayListSpy).add("Un valor"); // Revisamos que haya ejecutado el método una vez con un valor determinado
	}

}
