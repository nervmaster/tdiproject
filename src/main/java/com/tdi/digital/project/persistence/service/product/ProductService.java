package com.tdi.digital.project.persistence.service.product;

import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.repository.product.ProductRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private String EMPTY_STRING = "";

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

	public ProductEntity createOrReplaceProduct(ProductEntity entity)
	{
		Optional<ProductEntity> product = productRepository.findById(entity.getId());

		if(product.isPresent())
		{
			ProductEntity newEntity = product.get();
			newEntity.setName(entity.getName());
			newEntity.setDescription(entity.getDescription());
			newEntity.setPrice(entity.getPrice());
			newEntity.setQuantity(entity.getQuantity());

			newEntity = productRepository.save(newEntity);

			return newEntity;

		}
		return productRepository.save(entity);
	}

	public ProductEntity updateProduct(ProductEntity entity, Integer id) throws ResponseStatusException {
		ProductEntity entityToBeUpdated = getProductById(id);

		if(!EMPTY_STRING.equals(entity.getName())) {
			entityToBeUpdated.setName(entity.getName());
		}
		if(!EMPTY_STRING.equals(entity.getDescription())) {
			entityToBeUpdated.setDescription(entity.getDescription());
		}
		if(!EMPTY_STRING.equals(entity.getPrice())) {
			entityToBeUpdated.setPrice(entity.getPrice());
		}
		if(!EMPTY_STRING.equals(entity.getQuantity())) {
			entityToBeUpdated.setQuantity(entity.getQuantity());
		}

		return productRepository.save(entityToBeUpdated);
	}

	public void deleteProductById(Integer id) throws ResponseStatusException
	{
		Optional<ProductEntity> employee = productRepository.findById(id);

		if(employee.isPresent())
		{
			productRepository.deleteById(id);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
	}

}
