package com.gty.dsr.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gty.dsr.domain.Record;

public class RecordDAO implements RowMapper<Record> {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Record getRecordById(int id) {
		String SQL = "select * from record where id = ?";
		Record record = jdbcTemplate.queryForObject(SQL, new Object[] { id }, this);
		return record;
	}

	public Record getRecordByBranchAndDate(String branch, Date date) {
		try {
			String SQL = "select * from record where branch = ? and date = ?";
			Record record = jdbcTemplate.queryForObject(SQL, new Object[] { branch, date }, this);
			return record;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Record getPreviousRecord(Record record) {
		try {
			String SQL = "select * from record where branch = ? and date < ? order by date desc limit 1";
			Record previousRecord = jdbcTemplate.queryForObject(SQL, new Object[] { record.getBranch(), record.getDate() }, this);
			return previousRecord;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Record> getAllRecords() {
		String SQL = "select * from record";
		List<Record> records = jdbcTemplate.query(SQL, this);
		return records;
	}

	public void addRecord(Record record) {
		String SQL = "insert into record (branch, bank, date, consignment, overdue, advanced, open_consignment, due_consignment, new_consignment, sales, expense, deposit, ocoh, pcoh, acoh, diff, discrepancy_type, discrepancy_category, discrepancy_amount, additional_information) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(SQL, record.getBranch(), record.getBank(), record.getDate(), record.getConsignment(), record.getOverdue(), record.getAdvanced(), record.getOpenConsignment(), record.getDueConsignment(), record.getNewConsignment(), record.getSales(), record.getExpense(), record.getDeposit(), record.getOcoh(), record.getPcoh(), record.getAcoh(), record.getDiff(), record.getDiscrepancyType(), record.getDiscrepancyCategory(), record.getDiscrepancyAmount(), record.getAdditionalInformation());
	}

	public void updateRecord(Record record) {
		String SQL = "update record set branch = ?, bank = ?, date = ?, consignment = ?, overdue = ?, advanced = ?, open_consignment = ?, due_consignment = ?, new_consignment = ?, sales = ?, expense = ?, deposit = ?, ocoh = ?, pcoh = ?, acoh = ?, diff = ?, discrepancy_type = ?, discrepancy_category = ?, discrepancy_amount = ?, additional_information = ? where id = ?";
		jdbcTemplate.update(SQL, record.getBranch(), record.getBank(), record.getDate(), record.getConsignment(), record.getOverdue(), record.getAdvanced(), record.getOpenConsignment(), record.getDueConsignment(), record.getNewConsignment(), record.getSales(), record.getExpense(), record.getDeposit(), record.getOcoh(), record.getPcoh(), record.getAcoh(), record.getDiff(), record.getDiscrepancyType(), record.getDiscrepancyCategory(), record.getDiscrepancyAmount(), record.getAdditionalInformation(), record.getId());
	}

	public void updateBankOfRecords(String currentBankName, String newBankName) {
		String SQL = "update record set bank = ? where bank = ?";
		jdbcTemplate.update(SQL, newBankName, currentBankName);
	}

	public void updateBranchesOfRecords(String currentBranchName, String newBranchName) {
		String SQL = "update record set branch = ? where branch = ?";
		jdbcTemplate.update(SQL, newBranchName, currentBranchName);
	}

	public void updateDiscrepancyOfRecords(String currentDiscrepancyName, String newDiscrepancyName) {
		String SQL = "update record set discrepancy_category = ? where discrepancy_category = ?";
		jdbcTemplate.update(SQL, newDiscrepancyName, currentDiscrepancyName);
	}

	@Override
	public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
		Record record = new Record();
		record.setId(rs.getInt("id"));
		record.setBranch(rs.getString("branch"));
		record.setBank(rs.getString("bank"));
		record.setDate(rs.getDate("date"));
		record.setConsignment(rs.getInt("consignment"));
		record.setOverdue(rs.getInt("overdue"));
		record.setAdvanced(rs.getInt("advanced"));
		record.setOpenConsignment(rs.getInt("open_consignment"));
		record.setDueConsignment(rs.getInt("due_consignment"));
		record.setNewConsignment(rs.getInt("new_consignment"));
		record.setSales(rs.getBigDecimal("sales"));
		record.setExpense(rs.getBigDecimal("expense"));
		record.setDeposit(rs.getBigDecimal("deposit"));
		record.setOcoh(rs.getBigDecimal("ocoh"));
		record.setPcoh(rs.getBigDecimal("pcoh"));
		record.setAcoh(rs.getBigDecimal("acoh"));
		record.setDiff(rs.getBigDecimal("diff"));
		record.setDiscrepancyType(rs.getString("discrepancy_type"));
		record.setDiscrepancyCategory(rs.getString("discrepancy_category"));
		record.setDiscrepancyAmount(rs.getBigDecimal("discrepancy_amount"));
		record.setAdditionalInformation(rs.getString("additional_information"));
		return record;
	}
}
