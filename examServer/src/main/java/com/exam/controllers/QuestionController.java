package com.exam.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuizService quizService;
	
	//add Question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	//update Question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	//get Questiones
	@GetMapping("/")
	public ResponseEntity<Set<Question>> getQuestions(){
		return ResponseEntity.ok(this.questionService.getQuestions());
	}
	
	//get Question
	@GetMapping("/{questionId}")
	public ResponseEntity<Question> getQuestion(@PathVariable("questionId") Long questionId){
		return ResponseEntity.ok(this.questionService.getQuestion(questionId));
	}
	
	//get question of a quiz
	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId){
//it gives all the questions of that quiz
//		Quiz quiz = new Quiz();
//		quiz.setQid(quizId);
//		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
//		return ResponseEntity.ok(questionsOfQuiz);
		
		//it gives only required no of questions
		Quiz quiz = this.quizService.getQuiz(quizId);	
		Set<Question> questions = quiz.getQuestions();
		List list = new ArrayList(questions);
		if(list.size()>Integer.parseInt(quiz.getNoOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestions()+1));
		}
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
		
	}
	
	//get all questions of quiz
	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId){
//it gives all the questions of that quiz
		Quiz quiz = new Quiz();
		quiz.setQid(quizId);
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);		
		
	}
	
	//delete Question
	@DeleteMapping("/{questionId}")
	public ResponseEntity<Question> deleteQuestion(@PathVariable("questionId") Long questionId){
		this.questionService.deleteQuestion(questionId);
		return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
	}
	
	//evaluation of quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		  double marksGot=0;
		  int correctAnswers=0;
		  int attempted=0;
		for(Question q : questions){
			Question question = this.questionService.get(q.getQuesId());
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				correctAnswers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot+=marksSingle;
			}
			
			if(q.getAnswer()!=null){
			             attempted++;
			}
		};
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
}
