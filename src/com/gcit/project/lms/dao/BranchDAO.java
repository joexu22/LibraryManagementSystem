package com.gcit.project.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.project.lms.entity.Branch;

public class BranchDAO extends BaseDAO<Branch> {

	public BranchDAO(Connection connection) {
		super(connection);
	}

	public void addBranch(Branch branch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("insert into tbl_library_branch (branchId, branchName, branchAddress) values(?, ?, ?)", new Object[] { branch.getBranchId(), branch.getBranchName(), branch.getBranchAddress()});
	}

	public void editBranch(Branch branch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", new Object[]{branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
	}

	public void deleteBranch(Branch branch)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		save("delete from tbl_library_branch where branchId = ?", new Object[]{branch.getBranchId()});
	}

	public List<Branch> readAllBranches()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("select * from tbl_library_branch", null);
	}
	
	public List<Branch> readAllBranchsByName(String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return read("Select * from tbl_library_branch where branchName like ?", new Object[]{searchString});
	}
	
	public Branch readBranchByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Branch> branchs = read("Select * from tbl_library_branch where branchId = ?", new Object[]{pk});
		if(branchs!=null){
			return branchs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Branch> extractData(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Branch> branches = new ArrayList<>();
		while (rs.next()) {
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			branches.add(branch);
		}
		return branches;
	}

	@Override
	public List<Branch> extractDataFirstLevel(ResultSet rs)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
