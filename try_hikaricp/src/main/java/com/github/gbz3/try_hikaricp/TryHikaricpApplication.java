package com.github.gbz3.try_hikaricp;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TryHikaricpApplication {
	private Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Autowired
	PlatformTransactionManager tx;
	
	@Autowired
	CustomerDao dao;
	
	@RequestMapping( path = "/command/{cmd}", method = RequestMethod.POST )
	public String post( @PathVariable("cmd") String cmd, @RequestBody RequestResource rr ) throws Exception {
		logger.info( "cmd={} rr={}", cmd, rr.getParams() );
		
		try {
		
			final DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setTimeout( 5 );	// TODO prop
			
			TransactionStatus stat = tx.getTransaction( def );
			try {
				final String result = dao.finaAll().stream().map( s -> "id=" + s.getId() + " name=" + s.getName() + " email=" + s.getEmail() ).collect( Collectors.joining( "\n" ) );
				
				tx.commit( stat );
				return result;
			} catch ( Exception e ) {
				tx.rollback( stat );
				throw e;
			}
		} catch ( Exception e ) {
			logger.error( e.getMessage(), e );
			throw e;
		}
	}
	
	/** リクエストBody */
	public static class RequestResource implements Serializable {
		private static final long serialVersionUID = 1L;
		private List<String> params;
		public void setParams( List<String> p ) {
			params = p;
		}
		public List<String> getParams() {
			return params;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(TryHikaricpApplication.class, args);
	}
}
