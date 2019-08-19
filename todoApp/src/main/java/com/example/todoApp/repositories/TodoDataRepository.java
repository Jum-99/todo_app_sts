package com.example.todoApp.repositories;

import com.example.todoApp.TodoData;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoDataRepository extends JpaRepository<TodoData, Long> {
	
	public Optional<TodoData> findById(Long name);
	public List<TodoData> findByTitleLike(String name);

}
