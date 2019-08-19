package com.example.todoApp;

import java.io.Serializable;
import java.util.List;

public interface TodoDataDao <T> extends Serializable {
	public List<T> getAll();
}
