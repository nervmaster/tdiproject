package com.tdi.digital.project.persistence.service.product.read;

import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductReadService {

	@Autowired ProductRepository productRepository;

	public Iterable<ProductEntity> getAllProducts()
	{
		return productRepository.findAll();
	}

	public ProductEntity getProductById(Integer id) throws ResponseStatusException
	{
		Optional<ProductEntity> response = productRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
	}

}
