package com.gty.dsr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gty.dsr.domain.Discrepancy;

public class DiscrepancyDAO implements RowMapper<Discrepancy> {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Discrepancy getDiscrepancyById(int id) {
		String SQL = "select * from discrepancy where id = ?";
		Discrepancy discrepancy = jdbcTemplate.queryForObject(SQL, new Object[] { id }, this);
		return discrepancy;
	}

	public Discrepancy getDiscrepancyByDiscrepancyType(String type) {
		try {
			String SQL = "select * from discrepancy where type = ?";
			Discrepancy discrepancy = jdbcTemplate.queryForObject(SQL, new Object[] { type }, this);
			return discrepancy;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Discrepancy> getAllDiscrepancies() {
		String SQL = "select * from discrepancy order by type asc";
		List<Discrepancy> discrepancys = jdbcTemplate.query(SQL, this);
		return discrepancys;
	}

	public void addDiscrepancy(Discrepancy discrepancy) {
		String SQL = "insert into discrepancy (type, description) values (?, ?)";
		jdbcTemplate.update(SQL, discrepancy.getType(), discrepancy.getDescription());
	}

	public void udpateDiscrepancy(Discrepancy discrepancy) {
		String SQL = "update discrepancy set type = ?, description = ? where id = ?";
		jdbcTemplate.update(SQL, discrepancy.getType(), discrepancy.getDescription(), discrepancy.getId());
	}

	@Override
	public Discrepancy mapRow(ResultSet rs, int rowNum) throws SQLException {
		Discrepancy discrepancy = new Discrepancy();
		discrepancy.setId(rs.getInt("id"));
		discrepancy.setType(rs.getString("type"));
		discrepancy.setDescription(rs.getString("description"));
		return discrepancy;
	}
}
