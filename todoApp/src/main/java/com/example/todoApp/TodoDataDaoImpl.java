package com.example.todoApp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class TodoDataDaoImpl implements TodoDataDao<TodoData> {
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public TodoDataDaoImpl() {
		super();
	}
	public TodoDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}

	@Override
	public List<TodoData> getAll() {
		Query query = entityManager.createQuery("from TodoData");
		@SuppressWarnings("unchecked")
		List<TodoData> list = query.getResultList();
		entityManager.close();
		return list;
	}

}
