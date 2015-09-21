package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x38.rss.RssContent;

@Repository
@Transactional
public class RssDaoImpl implements RssDao {

	
	private JdbcTemplate jdbcTemplate;

	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public List<RssContent> getAll (){
		
		StringBuilder query = new StringBuilder("SELECT  ID, TITLE, DESCRIPTION, LINK, AUTHOR, CATEGORY, COMMENTS, ENCLOSURE, GUID, PUB_DATE, SOURCE"); 
		query.append(" FROM RSS_CONTENT ORDER BY PUB_DATE DESC");
		
		return (List<RssContent>) this.jdbcTemplate.query(query.toString(), this.rwMap);
	}
	
	
	private RowMapper<RssContent> rwMap = new RowMapper<RssContent>() {
		
		public RssContent mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			RssContent rssContent =  new RssContent();

			rssContent.setTitle(resultSet.getString("TITLE"));
			rssContent.setDescription(resultSet.getString("DESCRIPTION"));
			rssContent.setLink(resultSet.getString("LINK"));
			rssContent.setAuthor(resultSet.getString("AUTHOR"));
			rssContent.setCategory(resultSet.getString("CATEGORY"));
			rssContent.setComments(resultSet.getString("COMMENTS"));
			rssContent.setEnclosure(resultSet.getString("ENCLOSURE"));
			rssContent.setGuid(resultSet.getString("GUID"));
			rssContent.setPubDate(resultSet.getDate("PUB_DATE"));
			rssContent.setSource(resultSet.getString("SOURCE"));
			
			return rssContent;
		} 
	};
	
}
