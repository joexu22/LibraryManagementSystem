package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.project.lms.entity.Book;

public class BookDAO extends BaseDAO<Book> {

	public BookDAO(Connection connection) {
		super(connection);
	}

	public void addBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_book (title, pubId) values(?, ?)", new Object[] { book.getTitle(), book.getPublisher().getPublisherId() });
	}
	
	public Integer addBookWithID(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_book (title) values(?)", new Object[] { book.getTitle() });
	}

	public void editBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_book set title = ? where bookId = ?", new Object[]{book.getTitle(), book.getBookId()});
	}
	
	public void editBookPubId(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_book set pubId = ? where bookId = ?", new Object[]{ book.getPublisher().getPublisherId(), book.getBookId()});
	}

	public void deleteBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}

	public Book readBookWithId(int bookId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book where bookId = ?", new Object[] {bookId}).get(0);
	}

	public Book readBookByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Book> books = read("Select * from tbl_book where bookId = ?", new Object[]{pk});
		if(books!=null){
			return books.get(0);
		}else{
			return null;
		}
	}
	
	public List<Book> readAllBooks()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book", null);
	}

	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			book.setAuthors(adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[]{book.getBookId()}));
			book.setGenres(gdao.readFirstLevel("select * from tbl_genre where genre_id IN (select genre_id from tbl_book_genres where bookId = ?)", new Object[]{book.getBookId()}));
			books.add(book);
		}
		return books;
	}
	
	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			books.add(book);
		}
		return books;
	}
}
