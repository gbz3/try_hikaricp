package com.github.gbz3.try_hikaricp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
	private Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<CustomerDTO> finaAll() {
		logger.info( "CustomerDao#findAll()" );
		return jdbcTemplate.query(
				"SELECT * FROM customer",
				new BeanPropertyRowMapper<CustomerDTO>( CustomerDTO.class ) );
	}

}
