package com.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{

	public List<Quiz> findByCategory(Category category);
	public List<Quiz> findByActive(Boolean b);
	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);
}
