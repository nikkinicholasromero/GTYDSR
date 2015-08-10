package com.gty.dsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gty.dsr.domain.Branch;

public class BranchDAO implements RowMapper<Branch> {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Branch getBranchById(int id) {
		String SQL = "select * from branch where id = ?";
		Branch branch = jdbcTemplate.queryForObject(SQL, new Object[] { id }, this);
		return branch;
	}

	public Branch getBranchByName(String name) {
		String SQL = "select * from branch where name = ?";
		Branch branch = jdbcTemplate.queryForObject(SQL, new Object[] { name }, this);
		return branch;
	}

	public Branch getBranchByBranchName(String name) {
		try {
			String SQL = "select * from branch where name = ?";
			Branch branch = jdbcTemplate.queryForObject(SQL, new Object[] { name }, this);
			return branch;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Branch> getAllBranches() {
		String SQL = "select * from branch";
		List<Branch> branches = jdbcTemplate.query(SQL, this);
		return branches;
	}

	public void addBranch(Branch branch) {
		String SQL = "insert into branch (name, location, remittance_bank, proprietor, contact_number, status) values (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQL, branch.getName(), branch.getLocation(), branch.getRemittanceBank(), branch.getProprietor(), branch.getContactNumber(), branch.getStatus());
	}

	public void updateBranch(Branch branch) {
		String SQL = "update branch set name = ?, location = ?, remittance_bank = ?, proprietor = ?, contact_number = ?, status = ? where id = ?";
		jdbcTemplate.update(SQL, branch.getName(), branch.getLocation(), branch.getRemittanceBank(), branch.getProprietor(), branch.getContactNumber(), branch.getStatus(), branch.getId());
	}

	public void updateBankOfBranches(String currentBankName, String newBankName) {
		String SQL = "update branch set remittance_bank = ? where remittance_bank = ?";
		jdbcTemplate.update(SQL, newBankName, currentBankName);
	}

	@Override
	public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
		Branch branch = new Branch();
		branch.setId(rs.getInt("id"));
		branch.setName(rs.getString("name"));
		branch.setLocation(rs.getString("location"));
		branch.setRemittanceBank(rs.getString("remittance_bank"));
		branch.setProprietor(rs.getString("proprietor"));
		branch.setContactNumber(rs.getString("contact_number"));
		branch.setStatus(rs.getString("status"));
		return branch;
	}
}
