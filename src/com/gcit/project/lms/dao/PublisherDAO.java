package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.project.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection connection) {
		super(connection);
	}
	
	public void addPublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_publisher (publisherName, publisherAddress, publisherPhone) values (?, ?, ?)", new Object[] { publisher.getPubName(), publisher.getPubAddress(), publisher.getPubPhone()});
	}
	
	public void editPublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_publisher set publisherName = ?, publisherAddress = ?, publisherPhone = ? where publisherId = ?", new Object[]{publisher.getPubName(), publisher.getPubAddress(), publisher.getPubPhone(), publisher.getPublisherId()});
	}

	public void deletePublisher(Publisher publisher)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_publisher where publisherId = ?", new Object[]{publisher.getPublisherId()});
	}

	public List<Publisher> readAllPublisher()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_publisher", null);
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		List<Publisher> publishers = new ArrayList<>();
		while (rs.next()) {
			Publisher publisher = new Publisher();
			publisher.setPubName(rs.getString("publisherName"));
			publisher.setPubAddress(rs.getString("publisherAddress"));
			publisher.setPubPhone(rs.getString("publisherPhone"));
			publisher.setPublisherId(rs.getInt("publisherId"));
			publishers.add(publisher);
		}
		return publishers;
	}

	@Override
	public List<Publisher> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
