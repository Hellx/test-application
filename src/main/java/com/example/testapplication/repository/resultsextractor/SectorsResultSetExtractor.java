package com.example.testapplication.repository.resultsextractor;

import com.example.testapplication.domain.Sector;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SectorsResultSetExtractor implements ResultSetExtractor<List<Sector>> {
    @Override
    public List<Sector> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Sector> sectors = new ArrayList<>();
        while (rs.next()) {
            Sector sector = new Sector(
                    rs.getLong("option_value"),
                    rs.getString("sector_name")
            );
            sectors.add(sector);
        }
        return sectors;
    }
}
