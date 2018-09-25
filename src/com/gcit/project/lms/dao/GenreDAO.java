package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.project.lms.entity.Author;
import com.gcit.project.lms.entity.Genre;
import com.gcit.project.lms.entity.Publisher;

public class GenreDAO extends BaseDAO<Genre> {

	public GenreDAO(Connection connection) {
		super(connection);
	}
	
	public void addGenre(Genre genre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_genre (genre_name) values(?)", new Object[] { genre.getGenreName() });
	}
	
	public void deleteGenre(Genre genre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_genre where genre_id = ?", new Object[] { genre.getGenreId() });
	}
	
	public void editGenre(Genre genre) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[] { genre.getGenreName(), genre.getGenreId()});
		
	}
	
	public List<Genre> readAllGenre()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("select * from tbl_genre", null);
	}
	
	public Genre readGenreByPK(int pk) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Genre> genres = read("select * from tbl_genre where genre_id = ?", new Object[]{pk});
		if(genres!=null){
			return genres.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}

	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Genre> genres = new ArrayList<>();
		while (rs.next()) {
			Genre genre = new Genre();
			genre.setGenreId(rs.getInt("genre_id"));
			genre.setGenreName(rs.getString("genre_name"));
			genres.add(genre);
		}
		return genres;
	}
}
