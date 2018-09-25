/**
 * 
 */
package com.gcit.project.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.project.lms.dao.BookLoansDAO;
import com.gcit.project.lms.dao.BorrowerDAO;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.Borrower;
import com.gcit.project.lms.entity.Branch;

/**
 * @author joe
 *
 */
public class BorrowerService {
	ConnectionUtil connUtil = new ConnectionUtil();
	
	public List<Borrower> readAllBorrowerId() throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrower_dao = new BorrowerDAO(conn);
			return borrower_dao.readAllBorrowers();
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
	
	public List<Book> viewBooksAvailableForCheckout(Branch branch) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrower_dao = new BorrowerDAO(conn);
			return borrower_dao.viewAvailableBooks(branch.getBranchId());
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
	
	public void checkoutBook(Branch branch, Book book, Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrower_dao = new BorrowerDAO(conn);
			borrower_dao.checkoutBook(branch.getBranchId(), book.getBookId());
			borrower_dao.updateDueDate(branch.getBranchId(), book.getBookId(), borrower.getCardNo());
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
	
	public List<Branch> getBranchOfCheckedOutBooks(Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO book_loans_dao = new BookLoansDAO(conn);
			return book_loans_dao.viewBranchOfCheckedOutBooks(borrower);
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
	
	public List<Book> viewBooksAvailableForReturn(Branch branch, Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BookLoansDAO book_loan_dao = new BookLoansDAO(conn);
			return book_loan_dao.viewBooksCheckedOut(branch, borrower);
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
	
	public void returnBook(Branch branch, Book book, Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = connUtil.getConnection();
			BorrowerDAO borrower_dao = new BorrowerDAO(conn);
			borrower_dao.returnBook(branch.getBranchId(), book.getBookId());
			borrower_dao.updateCheckIn(branch.getBranchId(), book.getBookId(), borrower.getCardNo());
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
