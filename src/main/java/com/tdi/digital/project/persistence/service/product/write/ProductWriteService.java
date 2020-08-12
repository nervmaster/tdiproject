package com.tdi.digital.project.persistence.service.product.write;

import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductWriteService {
	@Autowired
	ProductRepository productRepository;

	private ProductEntity getProductById(Integer id) throws ResponseStatusException {
		Optional<ProductEntity> response = productRepository.findById(id);
		if(response.isPresent()) {
			return response.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
	}

	public ProductEntity createOrReplaceProduct(ProductEntity entity)
	{
		if(entity.getId() != null)
		{
			ProductEntity newEntity = getProductById(entity.getId());

			newEntity.setName(entity.getName());
			newEntity.setPrice(entity.getPrice());
			newEntity.setQuantity(entity.getQuantity());

			newEntity = productRepository.save(newEntity);

			return newEntity;
		}
		return productRepository.save(entity);
	}

	public ProductEntity updateProduct(ProductEntity entity) throws ResponseStatusException {
		ProductEntity entityToBeUpdated = getProductById(entity.getId());

		if(!isNullOrEmpty(entity.getName())) {
			entityToBeUpdated.setName(entity.getName());
		}
		if(entity.getPrice() != null) {
			entityToBeUpdated.setPrice(entity.getPrice());
		}
		if(entity.getQuantity() != null) {
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

	private boolean isNullOrEmpty(String s) {
		String EMPTY_STRING = "";
		return s == null || EMPTY_STRING.equals(s);
	}
}
