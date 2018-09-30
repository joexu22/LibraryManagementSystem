package com.gcit.project.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.project.lms.dao.AuthorDAO;
import com.gcit.project.lms.dao.BookAuthorDAO;
import com.gcit.project.lms.dao.BookDAO;
import com.gcit.project.lms.dao.BookGenreDAO;
import com.gcit.project.lms.dao.BookLoansDAO;
import com.gcit.project.lms.dao.BorrowerDAO;
import com.gcit.project.lms.dao.BranchDAO;
import com.gcit.project.lms.dao.GenreDAO;
import com.gcit.project.lms.dao.PublisherDAO;
import com.gcit.project.lms.entity.Author;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.BookAuthor;
import com.gcit.project.lms.entity.BookLoan;
import com.gcit.project.lms.entity.Borrower;
import com.gcit.project.lms.entity.Branch;
import com.gcit.project.lms.entity.Genre;
import com.gcit.project.lms.entity.Publisher;

public class AdminService {
	
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public void addBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.addBook(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.addAuthor(author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.addGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void addPublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.addPublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO bdao = new BranchDAO(conn);
			bdao.addBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void addBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.addBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}	
	
	public List<BookAuthor> viewBookAuthor() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookAuthorDAO ba_dao = new BookAuthorDAO(conn);
			return ba_dao.viewBookAuthor();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> readBook() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bk_dao = new BookDAO(conn);
			return bk_dao.readAllBooks();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Author> readAuthorOfBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookAuthorDAO bk_a_dao = new BookAuthorDAO(conn);
			return bk_a_dao.readAuthorOfBook(book);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Genre> readGenreOfBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookGenreDAO bk_g_dao = new BookGenreDAO(conn);
			return bk_g_dao.readGenreOfBook(book);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Author> readAuthor() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO a_dao = new AuthorDAO(conn);
			return a_dao.readAllAuthors();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Genre> readGenre() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO g_dao = new GenreDAO(conn);
			return g_dao.readAllGenre();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Publisher> readPublisher() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO p_dao = new PublisherDAO(conn);
			return p_dao.readAllPublisher();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Branch> readBranch() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO br_dao = new BranchDAO(conn);
			return br_dao.readAllBranches();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Borrower> readBorrower() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bw_dao = new BorrowerDAO(conn);
			return bw_dao.readAllBorrowers();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void updateBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bk_dao = new BookDAO(conn);
			bk_dao.editBook(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBookPubId(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO bk_dao = new BookDAO(conn);
			bk_dao.editBookPubId(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBookAuthor(Book book, Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookAuthorDAO bk_a_dao = new BookAuthorDAO(conn);
			bk_a_dao.addBookAuthor(book, author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void updateBookGenre(Book book, Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookGenreDAO bk_g_dao = new BookGenreDAO(conn);
			bk_g_dao.addBookGenre(book, genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO a_dao = new AuthorDAO(conn);
			a_dao.editAuthor(author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO g_dao = new GenreDAO(conn);
			g_dao.editGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void updatePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO p_dao = new PublisherDAO(conn);
			p_dao.editPublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBrach(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO br_dao = new BranchDAO(conn);
			br_dao.editBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bw_dao = new BorrowerDAO(conn);
			bw_dao.editBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	
	public void deleteBook(Book book) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO b_dao = new BookDAO(conn);
			b_dao.deleteBook(book);
			conn.commit();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteAuthor(Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			AuthorDAO a_dao = new AuthorDAO(conn);
			a_dao.deleteAuthor(author);
			conn.commit();			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBookAuthor(Book book, Author author) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookAuthorDAO bk_a_dao = new BookAuthorDAO(conn);
			bk_a_dao.deleteBookAuthor(book, author);
			conn.commit();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBookGenre(Book book, Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookGenreDAO bk_g_dao = new BookGenreDAO(conn);
			bk_g_dao.deleteBookGenre(book, genre);
			conn.commit();
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteGenre(Genre genre) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			GenreDAO g_dao = new GenreDAO(conn);
			g_dao.deleteGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			PublisherDAO p_dao = new PublisherDAO(conn);
			p_dao.deletePublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBranch(Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO br_dao = new BranchDAO(conn);
			br_dao.deleteBranch(branch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO bw_dao = new BorrowerDAO(conn);
			bw_dao.deleteBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<BookLoan> viewDueDates() throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.viewDueDates();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void overrideDueDate(BookLoan bookLoan, String dueDate) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			bldao.overrideDueDate(bookLoan.getBranchId(), bookLoan.getBookId(), bookLoan.getBorrowerId(), dueDate);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			if(conn!=null){
				conn.rollback();
			}
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}

}
