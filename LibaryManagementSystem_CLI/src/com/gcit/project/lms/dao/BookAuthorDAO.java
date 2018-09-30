package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.project.lms.entity.Author;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.BookAuthor;

public class BookAuthorDAO extends BaseDAO<BookAuthor> {

	public BookAuthorDAO(Connection connection) {
		super(connection);
	}
	
	public void addBookAuthor(Book book, Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert ignore into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] { book.getBookId(), author.getAuthorId() });
	}
	
	public void deleteBookAuthor(Book book, Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book_authors where bookId = ? and authorId = ?", new Object[]{book.getBookId(), author.getAuthorId()});
	}

	public List<Author> readAuthorOfBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Author> output = new ArrayList<>();
		String sql = "select * from tbl_book_authors where bookId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, book.getBookId());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			AuthorDAO author_dao = new AuthorDAO(conn);
			Author author = new Author();
			author = author_dao.readAuthorByPK(rs.getInt("authorId"));
			output.add(author);
		}
		return output;
	}
	
	public List<BookAuthor> viewBookAuthor() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookAuthor> output = new ArrayList<>();
		String sql = "select * from tbl_book_authors";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BookAuthor ba = new BookAuthor();
			
			BookDAO book_dao = new BookDAO(conn);
			String bookTitle = book_dao.readBookWithId(rs.getInt("bookId")).getTitle();

			AuthorDAO author_dao = new AuthorDAO(conn);
			String bookAuthor = author_dao.readAuthorByPK(rs.getInt("authorId")).getAuthorName();

			ba.setAuthorId(rs.getInt("authorId"));
			ba.setBookId(rs.getInt("bookId"));
			ba.setBookTitle(bookTitle);
			ba.setBookAuthor(bookAuthor);
			output.add(ba);
		}
		return output;
	}

	@Override
	public List<BookAuthor> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookAuthor> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}