package com.SpringBootAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository prodRepository;
	
	public List<Product> listAll() {
		return prodRepository.findAll();
	}
	
	public void save(Product product) {
		prodRepository.save(product);
	}
	
	public Product get(Integer id) {
		return prodRepository.findById(id).get();
	}
	
	public void delete(Integer id) {
		prodRepository.deleteById(id);
	}
} 
