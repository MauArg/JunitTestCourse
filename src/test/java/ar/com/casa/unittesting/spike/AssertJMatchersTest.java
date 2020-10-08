package ar.com.casa.unittesting.spike;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertJMatchersTest {

	@Test
	public void learning() {
		List<Integer> numbers = Arrays.asList(12, 15, 45);

		// Tipos de asserts que se pueden hacer con AssertJ (muy parecidos a hamcrest)
		// pero es encadenado y permite varias assertions encadenadas y permite
		// programación funcional con funciones Lambda
		assertThat(numbers).hasSize(3).contains(12, 15).allMatch(x -> x > 10).allMatch(x -> x < 100)
				.noneMatch(x -> x < 0);

		assertThat("").isEmpty();
		assertThat("ABCDE").contains("BCD").startsWith("ABC").endsWith("CDE");

	}

}
