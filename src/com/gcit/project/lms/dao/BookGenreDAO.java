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
import com.gcit.project.lms.entity.BookGenre;
import com.gcit.project.lms.entity.Genre;

public class BookGenreDAO extends BaseDAO<BookGenre> {

	public BookGenreDAO(Connection connection) {
		super(connection);
	}
	
	public void addBookGenre(Book book, Genre genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert ignore into tbl_book_genres (genre_id, bookId) values (?, ?)", new Object[] { genre.getGenreId(), book.getBookId() });
	}
	
	public void deleteBookGenre(Book book, Genre genre)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_book_genres where genre_id = ? and bookId = ?", new Object[]{ genre.getGenreId(), book.getBookId()});
	}

	public List<Genre> readGenreOfBook(Book book) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Genre> output = new ArrayList<>();
		String sql = "select * from tbl_book_genres where bookId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, book.getBookId());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			GenreDAO g_dao = new GenreDAO(conn);
			Genre genre = new Genre();
			genre = g_dao.readGenreByPK(rs.getInt("genre_id"));
			output.add(genre);
		}
		return output;
	}

	@Override
	public List<BookGenre> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookGenre> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}