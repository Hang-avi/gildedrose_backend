package com.perficient.praxis.gildedrose;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GildedroseApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testSum(){

		int a = 10;
		int b = 12;

		int actualResult = Math.multiplyExact(a, b);

		assertEquals(120, actualResult);
	}

}
