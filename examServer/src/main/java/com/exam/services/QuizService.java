package com.exam.services;

import java.util.List;
import java.util.Set;


import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public Set<Quiz> getQuizes();
	public Quiz getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	public List<Quiz> getQuizesOfCategory(Category category);
	public List<Quiz> getActiveQuizes();
	public List<Quiz> getActiveQuizesOfCategory(Category category);
}
