/**
 * 
 */
package com.gcit.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author joe
 *
 */

@Entity
@Table(name="tbl_genre", catalog="library")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="genreName", scope=Genre.class)	
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {
	@Id
	@Column(name = "genre_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer genreId;
	
	@NotEmpty
	@Column(name="genre_name")
	@Length(max=45)
	private String genreName;
	
//	private List<Book> books;
	
	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	/**
	 * @return the books
	 */
//	public List<Book> getBooks() {
//		return books;
//	}
//	/**
//	 * @param books the books to set
//	 */
//	public void setBooks(List<Book> books) {
//		this.books = books;
//	}
}
