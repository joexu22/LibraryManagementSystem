package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.gcit.project.lms.entity.Author;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.BookLoan;
import com.gcit.project.lms.entity.Borrower;
import com.gcit.project.lms.entity.Branch;

public class BookLoansDAO extends BaseDAO<Borrower> {

	public BookLoansDAO(Connection connection) {
		super(connection);
	}

	public void addAuthor(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_author (authorName) values(?)", new Object[] { author.getAuthorName() });
	}
	
	public List<Branch> viewBranchOfCheckedOutBooks(Borrower borrower) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Branch> branch_list = new ArrayList<>();
		String sql = "select branchId from tbl_book_loans where cardNo = ?";		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, borrower.getCardNo());
		ResultSet rs = pstmt.executeQuery();
		HashSet<Object> branchIds = new HashSet<>();
		while (rs.next()) {
			branchIds.add(rs.getInt("branchId"));
		}
		List<Object> branchIdList = new ArrayList<>(branchIds);
		BranchDAO branch_dao = new BranchDAO(conn);
		for (Object id : branchIdList) {
			branch_list.add(branch_dao.readBranchByPK((Integer) id));
		}
		return branch_list;
	}
	
	public List<Book> viewBooksCheckedOut(Branch branch, Borrower borrower) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<Book> book_list = new ArrayList<>();
		String sql = "select bookId from tbl_book_loans where branchId = ? and cardNo = ? and dateIn is NULL";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, branch.getBranchId());
		pstmt.setObject(2, borrower.getCardNo());
		ResultSet rs = pstmt.executeQuery();
		HashSet<Object> bookIds = new HashSet<>();
		while (rs.next()) {
			bookIds.add(rs.getInt("bookId"));
		}
		List<Object> bookIdList = new ArrayList<>(bookIds);
		BookDAO book_dao = new BookDAO(conn);
		for (Object id : bookIdList) {
			book_list.add(book_dao.readBookWithId((int) id));
		}
		return book_list;
	}
	
	public void addCheckInDateTime() {
		
	}
	
	//SOMEWHAT OF A HACK... NOT USED
	public ResultSet getDueDateIds() throws SQLException {
		String sql = "select * from tbl_book_loans";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	//TODO: View Book Due Dates
	public List<BookLoan> viewDueDates() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<BookLoan> output = new ArrayList<>();
		String sql = "select * from tbl_book_loans";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			BookLoan bl = new BookLoan();
			
			BookDAO book_dao = new BookDAO(conn);
			String bookName = book_dao.readBookWithId(rs.getInt("bookId")).getTitle();
			
			BranchDAO branch_dao = new BranchDAO(conn);
			String branchName = branch_dao.readBranchByPK(rs.getInt("branchId")).getBranchName();
			
			BorrowerDAO borrower_dao = new BorrowerDAO(conn);
			String borrowerName = borrower_dao.readBorrowerByPK(rs.getInt("cardNo")).getName();

			String dateDue = rs.getString("dueDate");

			bl.setBookId(rs.getInt("bookId"));
			bl.setBranchId(rs.getInt("branchId"));
			bl.setBorrowerId(rs.getInt("cardNo"));
			bl.setBookName(bookName);
			bl.setBranchName(branchName);
			bl.setBorrowerName(borrowerName);
			bl.setDateDue(dateDue);
			output.add(bl);
		}
		return output;
	}
	
	public void overrideDueDate(int branchId, int bookId, int cardNo, String dueDate) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setObject(1, dueDate);
		pstmt.setObject(2, bookId);
		pstmt.setObject(3, branchId);
		pstmt.setObject(4, cardNo);
		pstmt.executeUpdate();
	}

	@Override
	public List<Borrower> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
