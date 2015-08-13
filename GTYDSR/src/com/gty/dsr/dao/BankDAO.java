package com.gty.dsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gty.dsr.domain.Bank;

public class BankDAO implements RowMapper<Bank> {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Bank getBankById(int id) {
		String SQL = "select * from bank where id = ?";
		Bank bank = jdbcTemplate.queryForObject(SQL, new Object[] { id }, this);
		return bank;
	}

	public Bank getBankByBankName(String name) {
		try {
			String SQL = "select * from bank where name = ?";
			Bank bank = jdbcTemplate.queryForObject(SQL, new Object[] { name }, this); 
			return bank;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Bank> getAllBanks() {
		String SQL = "select * from bank order by name asc";
		List<Bank> banks = jdbcTemplate.query(SQL, this);
		return banks;
	}

	public List<Bank> getAllActiveBanks() {
		String SQL = "select * from bank where status = 'Active' order by name asc";
		List<Bank> banks = jdbcTemplate.query(SQL, this);
		return banks;
	}

	public void addBank(Bank bank) {
		String SQL = "insert into bank (name, status) values (?, ?)";
		jdbcTemplate.update(SQL, bank.getName(), bank.getStatus());
	}

	public void updateBank(Bank bank) {
		String SQL = "update bank set name = ?, status = ? where id = ?";
		jdbcTemplate.update(SQL, bank.getName(), bank.getStatus(), bank.getId());
	}

	@Override
	public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
		Bank bank = new Bank();
		bank.setId(rs.getInt("id"));
		bank.setName(rs.getString("name"));
		bank.setStatus(rs.getString("status"));
		return bank;
	}
}
