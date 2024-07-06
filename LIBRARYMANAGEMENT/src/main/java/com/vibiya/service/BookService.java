package com.vibiya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.BookException;
import com.vibiya.exception.LoginException;
import com.vibiya.exception.UserException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.Book;
import com.vibiya.model.User;
import com.vibiya.model.UserLoginSession;
import com.vibiya.repository.AdminDAO;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.BookDAO;
import com.vibiya.repository.UserDAO;
import com.vibiya.repository.UserSessionDAO;

@Service
public class BookService {

	@Autowired
	AdminSessionDAO adminSessionDAO;
	@Autowired
	UserSessionDAO userSessionDAO;
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	BookDAO bookDAO;
	
	
	
	
	public Book addBook(Book book,String key) throws LoginException, BookException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		Optional<Book> existingBook = bookDAO.findById(book.getBookId());
		if(existingBook.isPresent())
		{
			throw new BookException("Book already exists");
		}
		book.setBorrowedBy(null);
		return bookDAO.save(book);
    }

	public List<Book> findByTitle(String title) throws BookException
	{
		if(title.isEmpty())
		{
			throw new BookException("No such book exists");
		}
		return bookDAO.findBytitle(title);
	}
	
	public List<Book> findByAuthor(String author) throws BookException
	{
		if(author.isEmpty())
		{
			throw new BookException("No such book exists");
		}
		return bookDAO.findByAuthor(author);
	}

	public List<Book> findAllBooks(String key) throws LoginException
	{
		UserLoginSession loggedInUser = userSessionDAO.findByUuid(key);
		if(loggedInUser==null)
		{
			throw new LoginException("Please Login first!!");
		}
		return bookDAO.findAll();
	}
	
	public Book updateBook(Book book,String key) throws LoginException, BookException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
	Book existingBook= bookDAO.findById(book.getBookId()).orElseThrow(()->new BookException("Book with Id " +book.getBookId() +" is not present"));
	existingBook.setAuthor(book.getAuthor());
    existingBook.setTitle(book.getTitle());
    bookDAO.save(existingBook);
    return existingBook;
	}
	
	public void deleteBook(Integer bookId,String key) throws LoginException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		bookDAO.deleteById(bookId);
	}

	public Book borrowBook(Integer bookId,String key) throws BookException, UserException, LoginException
	{
		UserLoginSession loggedInUser = userSessionDAO.findByUuid(key);
		if(loggedInUser==null)
		{
			throw new LoginException("Please Login first!!");
		}
		User user =userDAO.findById(loggedInUser.getUserId()).orElseThrow(()->new UserException("No such User exists"));
		
		Book existingBook= bookDAO.findById(bookId).orElseThrow(()->new BookException("Book with Id " +bookId +" is not present"));
		
		if(!existingBook.isAvailability())
		{
			throw new BookException("Book with Id "+bookId+" is already borrowed" );
		}
		
		existingBook.setAvailability(false);
		existingBook.setBorrowedBy(user);
		bookDAO.save(existingBook);
		return existingBook;
	}
	
	
	public Book returnBook(Integer bookId,String key) throws BookException, LoginException
	{
		UserLoginSession loggedInUser = userSessionDAO.findByUuid(key);
		if(loggedInUser==null)
		{
			throw new LoginException("Please Login first!!");
		}
		Book book= bookDAO.findById(bookId).orElseThrow(()->new BookException("Returning book details are not correct"));
		
		if(book.isAvailability())
		{
			throw new IllegalStateException("Book with Id "+bookId+" is not borrowed");
		}
		
		book.setAvailability(true);
		book.setBorrowedBy(null);
		bookDAO.save(book);
		return book;
		
	}
	
	
	public List<Book> searchBook(String author,String title)
	{
		if(title!=null)
		{
			return bookDAO.findBytitle(title);
		}
		else if(author!=null)
		{
			return bookDAO.findByAuthor(author);
		}
		else
		return bookDAO.findAll();
	}

}
