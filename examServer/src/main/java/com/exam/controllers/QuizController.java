package com.exam.controllers;

import java.util.List;
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
import com.exam.models.exam.Quiz;
import com.exam.services.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	//add quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	//update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	//get quizes
	@GetMapping("/")
	public ResponseEntity<Set<Quiz>> getQuizes(){
		return ResponseEntity.ok(this.quizService.getQuizes());
	}
	
	//get quiz
	@GetMapping("/{quizId}")
	public ResponseEntity<Quiz> getQuiz(@PathVariable("quizId") Long quizId){
		return ResponseEntity.ok(this.quizService.getQuiz(quizId));
	}
	
	//delete quiz
	@DeleteMapping("/{quizId}")
	public ResponseEntity<Quiz> deleteQuiz(@PathVariable("quizId") Long quizId){
		this.quizService.deleteQuiz(quizId);
		return new ResponseEntity<Quiz>(HttpStatus.NO_CONTENT);
	}
	
	//particular category quizes
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizesOfCategory(@PathVariable("cid") Long cId){
		Category category = new Category();
		category.setCid(cId);
		return this.quizService.getQuizesOfCategory(category);
	}
	
	//get Active quizes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizes(){
		return this.quizService.getActiveQuizes();
	}
	
	//get active quizes of category
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizesOfCategory(@PathVariable("cid") Long cId){
		Category category = new Category();
		category.setCid(cId);
		return this.quizService.getActiveQuizesOfCategory(category);
	}
	
}
