package ar.com.casa.unittesting.spike;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class HamcrestMatchersTest {

	@Test
	public void learning() {
		List<Integer> numbers = Arrays.asList(12, 15, 45);

		// Tipos de asserts que se pueden hacer con Hamcrest
//		assertThat(numbers, hasSize(3));
//		assertThat(numbers, hasItems(12, 45));
//		assertThat(numbers, everyItem(greaterThan(10)));
//		assertThat(numbers, everyItem(lessThan(100)));
//
//		assertThat("", emptyString());
//		assertThat("ABCDE", containsString("BCD"));
//		assertThat("ABCDE", endsWith("CDE"));
		
		

	}

}
