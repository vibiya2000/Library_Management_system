package com.vibiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.exception.BookException;
import com.vibiya.exception.LoginException;
import com.vibiya.exception.UserException;
import com.vibiya.model.Book;
import com.vibiya.service.BookService;

import jakarta.validation.Valid;

@RestController
public class BookController {
	

	@Autowired
	private BookService bookService;
	


	@PostMapping("/addBook")
	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book,@RequestParam String key) throws LoginException, BookException
	{
		return new ResponseEntity<Book>(bookService.addBook(book,key), HttpStatus.CREATED);
	}
	
	@GetMapping("/findByTitle/{title}")
	public ResponseEntity<List<Book>> findByTitle(@PathVariable String title ) throws BookException
	{
		return new ResponseEntity<List<Book>>(bookService.findByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping("/findByAuthor/{author}")
	public ResponseEntity<List<Book>> findByAuthor (@PathVariable String author ) throws BookException
	{
		return new ResponseEntity<List<Book>>(bookService.findByAuthor(author), HttpStatus.OK);
	}
	@GetMapping("/findAllBooks")
	public List<Book> findAllBooks(@RequestParam String key) throws LoginException
	{
		return bookService.findAllBooks(key);
	}
	
	@PutMapping("/updateBook/{bookId}")
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book,@RequestParam String key) throws LoginException, BookException
	{
		return new ResponseEntity<Book>(bookService.updateBook(book,key), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer bookId,@RequestParam String key) throws LoginException
	{
		bookService.deleteBook(bookId, key);
		return new ResponseEntity<String>("Book with id "+bookId+" deleted succesfully", HttpStatus.OK);
	}
	
	@PostMapping("/borrowBook/{bookId}")
	public ResponseEntity<Book> borrowBook(@PathVariable Integer bookId,@RequestParam String key) throws BookException, UserException, LoginException
	{
		return new ResponseEntity<Book>(bookService.borrowBook(bookId,key), HttpStatus.OK);
	}
	
	@PostMapping("/returnBook/{bookId}")
	public ResponseEntity<Book> returnBook(@PathVariable Integer bookId,@RequestParam String key) throws BookException, LoginException
	{
		return new ResponseEntity<Book>(bookService.returnBook(bookId,key), HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBook(@RequestParam(required = false) String author,@RequestParam(required = false) String title)
	{
		
		return new ResponseEntity<List<Book>>(bookService.searchBook(author, title), HttpStatus.OK);
	}
	


}
