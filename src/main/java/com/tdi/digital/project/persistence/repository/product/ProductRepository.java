package com.tdi.digital.project.persistence.repository.product;

import com.tdi.digital.project.entitiy.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

}