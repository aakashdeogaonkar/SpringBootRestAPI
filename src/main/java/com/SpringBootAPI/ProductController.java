package com.SpringBootAPI;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/products")
	public List<Product> listAll() {
		return prodService.listAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		try {
			Product product = prodService.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/products")
	public void add(@RequestBody Product product) {
		prodService.save(product);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
//		try {
//		Product existProduct = prodService.get(id);
//		prodService.save(existProduct);
//		return new ResponseEntity<>(existProduct, HttpStatus.OK);
//		} catch (NoSuchElementException e) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		
		Product prod = prodService.get(id);
		if(prod == null) {
			return ResponseEntity.notFound().build();
		}
		
		prod.setName(product.getName());
		prod.setPrice(product.getPrice());
		
		prodService.save(prod);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable Integer id) {
		prodService.delete(id);
	}
	
}
