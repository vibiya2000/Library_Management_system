package com.vibiya.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.Book;

public interface BookDAO extends JpaRepository<Book, Integer>{

	
	 public List<Book> findByAuthor(String author);
	 public List<Book> findBytitle(String title);
	//public Object findBySingleTitle(String title);
	//public Object findBySingleAuthor(String author);


}
