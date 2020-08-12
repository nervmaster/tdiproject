package com.tdi.digital.project.controller.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests declared with no implementation because it takes a significant effort
 * to mock the persistence services.
 */

/**
 * Test class for {@link ProductController}.
 */
@SpringBootTest
public class TestProductController {
	@Autowired
	private ProductController productController;

	@Test
	public void shouldLoadSuccessfully()
	{
		assertThat(productController).isNotNull();
	}

	@Test
	public void shouldReturnAllProducts(){}

	@Test
	public void shouldReturnProductById(){}

	@Test
	public void shouldCreateProduct(){}

	@Test
	public void shouldReplaceProduct(){}

	@Test
	public void shouldUpdateProduct(){}
}
