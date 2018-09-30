package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.Borrower;
import com.gcit.project.lms.entity.Branch;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection connection) {
		super(connection);
	}

	public void addBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_borrower (name, address, phone) values(?, ?, ?)",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone() });
	}

	public void editBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_borrower where cardNo = ?", new Object[] { borrower.getCardNo() });
	}

	public List<Borrower> readAllBorrowers()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("select * from tbl_borrower", null);
	}

	public List<Book> viewAvailableBooks(int branchId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "select bookId from tbl_book_copies where branchId = ? and noOfCopies > 0";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, branchId);
		ResultSet rs = pstmt.executeQuery();

		BookDAO b_dao = new BookDAO(conn);
		List<Book> book_list = new ArrayList<>();
		while (rs.next()) {
			Book book = new Book();
			int book_id = rs.getInt("bookId");
			book = b_dao.readBookWithId(book_id);
			book_list.add(book);
		}
		return book_list;
	}

	public void checkoutBook(int branchId, int bookId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// TODO: Interesting Thing About the Schema Of Database - Does noOfCopies mean
		// total copies or number of copies available currently
		String sql = "update tbl_book_copies set noOfCopies = (noOfCopies - 1) where branchId = ? and bookId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, branchId);
		pstmt.setObject(2, bookId);
		pstmt.executeUpdate();
	}

	public void returnBook(int branchId, int bookId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "update tbl_book_copies set noOfCopies = (noOfCopies + 1) where branchId = ? and bookId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, branchId);
		pstmt.setObject(2, bookId);
		pstmt.executeUpdate();
	}

	public void updateDueDate(int branchId, int bookId, int cardNo)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "insert ignore into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) values (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, bookId);
		pstmt.setObject(2, branchId);
		pstmt.setObject(3, cardNo);

		Date date = new Date();
		long time = date.getTime();
		Timestamp sqlTime = new Timestamp(time);

		// time logic for due date
		int noOfDays = 7; // i.e a week
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
		Date nextDate = calendar.getTime();
		long nextTime = nextDate.getTime();
		Timestamp sqlNextTime = new Timestamp(nextTime);

		pstmt.setObject(4, sqlTime);
		pstmt.setObject(5, sqlNextTime);
		pstmt.executeUpdate();
	}

	public void updateCheckIn(int branchId, int bookId, int cardNo)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		String sql = "update tbl_book_loans set dateIn = ? where bookId = ? and branchId = ? and cardNo = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		Date date = new Date();
		long time = date.getTime();
		Timestamp sqlTime = new Timestamp(time);

		pstmt.setObject(1, sqlTime);
		pstmt.setObject(2, bookId);
		pstmt.setObject(3, branchId);
		pstmt.setObject(4, cardNo);
		pstmt.executeUpdate();
	}

	public Borrower readBorrowerByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Borrower> branchs = read("Select * from tbl_borrower where cardNo = ?", new Object[] { pk });
		if (branchs != null) {
			return branchs.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Borrower> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Borrower> borrowers = new ArrayList<>();
		while (rs.next()) {
			Borrower borrower = new Borrower();
			borrower.setCardNo(rs.getInt("cardNo"));
			borrower.setName(rs.getString("name"));
			borrower.setAddress(rs.getString("address"));
			borrower.setPhone(rs.getString("phone"));
			borrowers.add(borrower);
		}
		return borrowers;
	}

	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
