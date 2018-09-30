package com.gcit.project.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.project.lms.dao.BookCopiesDAO;
import com.gcit.project.lms.dao.BookDAO;
import com.gcit.project.lms.dao.BranchDAO;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.Branch;

public class LibrarianService {
	
	ConnectionUtil connUtil = new ConnectionUtil();

	public List<Branch> readBranch() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branch_dao = new BranchDAO(conn);
			return branch_dao.readAllBranches();
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
	
	public void editBranch(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BranchDAO branch_dao = new BranchDAO(conn);
			branch_dao.editBranch(branch);
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
	
	public List<Book> viewBooks() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookDAO book_dao = new BookDAO(conn);
			return book_dao.readAllBooks();
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

	public int viewNumOfBookCopies(Book book, Branch branch) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bc_dao = new BookCopiesDAO(conn);
			return bc_dao.viewNumberOfCopies(book.getBookId(), branch.getBranchId());
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
		return 0;
	}
	
	public void changeNumOfBookCopies(Book book, Branch branch, int number) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookCopiesDAO bc_dao = new BookCopiesDAO(conn);
			bc_dao.changeNumberOfCopies(book.getBookId(), branch.getBranchId(), number);
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