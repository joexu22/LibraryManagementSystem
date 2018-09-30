package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gcit.project.lms.entity.Book;

public class BookCopiesDAO extends BaseDAO<Book> {

	public BookCopiesDAO(Connection connection) {
		super(connection);
	}

	public int viewNumberOfCopies(int bookId, int branchId) throws SQLException {
		String sql = "select noOfCopies from tbl_book_copies where bookId = ? and branchId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, bookId);
		pstmt.setObject(2, branchId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {			
			return rs.getInt("noOfCopies");
		} else {
			return 0;
		}
	}
	
	public void changeNumberOfCopies(int bookId, int branchId, int noOfCopies) throws SQLException {
		String sql = "insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?, ?, ?) on duplicate key update noOfCopies = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, bookId);
		pstmt.setObject(2, branchId);
		pstmt.setObject(3, noOfCopies);
		pstmt.setObject(4, noOfCopies);
		pstmt.executeUpdate();
	}
	
	public void addBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_book (title) values(?)", new Object[] { book.getTitle() });
	}
	
	public Integer addBookWithID(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return saveWithID("insert into tbl_book (title) values(?)", new Object[] { book.getTitle() });
	}

	public void editBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_book set title = ? where bookId = ?", new Object[]{book.getTitle(), book.getBookId()});
	}

	public void deleteBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book where bookId = ?", new Object[]{book.getBookId()});
	}

	public List<Book> readAllBooks()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_book", null);
	}

	@Override
	public List<Book> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
