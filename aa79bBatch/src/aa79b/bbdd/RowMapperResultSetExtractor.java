/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aa79b.bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Juergen Hoeller
 * @since 1.0.2
 * @see RowMapper
 * @see JdbcTemplate
 * @see org.springframework.jdbc.object.MappingSqlQuery
 */
public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    private final int rowsExpected;


    /**
     * Create a new RowMapperResultSetExtractor.
     * @param rowMapper the RowMapper which creates an object for each row
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        this(rowMapper, 0);
    }

    /**
     * Create a new RowMapperResultSetExtractor.
     * @param rowMapper the RowMapper which creates an object for each row
     * @param rowsExpected the number of expected rows
     * (just used for optimized collection handling)
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }


    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        final List<T> results = this.rowsExpected > 0 ? new ArrayList<T>(this.rowsExpected) : new ArrayList<T>();
        int rowNum = 0;
        while (rs.next()) {
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }

}
