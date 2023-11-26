package com.exam.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.exam.Category;
import com.exam.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category){
		Category addCategory = this.categoryService.addCategory(category);
		return ResponseEntity.ok(addCategory);
	}
	
	//getCategory
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Long categoryId){
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
	
	//getCategories
	@GetMapping("/")
	public ResponseEntity<Set<Category>> getCategories(){
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	//update category
	@PutMapping("/")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		return ResponseEntity.ok(this.categoryService.updateCategory(category));
	}
	
	//delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("categoryId") Long categoryId){
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
