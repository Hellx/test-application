package com.example.testapplication.repository;

import com.example.testapplication.domain.*;
import com.example.testapplication.repository.resultsextractor.SectorsResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class SectorsRepository extends BaseRepository {

    public List<Sector> getAllSectors() {
        String sql = "SELECT option_value, sector_name FROM public.sectors";
        return namedParameterJdbcTemplate.query(sql, new SectorsResultSetExtractor());
    }

    public Boolean getPersonalSectorsUid(String uid) {
        String sql = "SELECT (EXISTS(SELECT uid FROM personal_sectors WHERE uid = ?)) AS exists";

        return jdbcTemplate.queryForObject(sql, Boolean.class, uid);
    }

    public Boolean getSessionDetailsUid(String uid) {
        String sql = "SELECT (EXISTS(SELECT uid FROM session_details WHERE uid = ?)) AS exists";

        return jdbcTemplate.queryForObject(sql, Boolean.class, uid);
    }

    public int deletePersonalSector(String uid) {
        String sql = "DELETE FROM personal_sectors WHERE uid = ?" ;

        return jdbcTemplate.update(sql, uid);
    }

    public int deleteSessionDetails(String uid) {
        String sql = "DELETE FROM session_details WHERE uid = ?" ;

        return jdbcTemplate.update(sql, uid);
    }

    public Long addPersonalSector(PersonalSector personalSector, SessionData sessionData) {
        String sql = "INSERT INTO personal_sectors(uid, sector_id, created_ts)" +
                " VALUES (:uid, :sectorId, :createdTs) RETURNING id" ;

        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource()
                .addValue("uid", sessionData.getUid())
                .addValue("sectorId", personalSector.getSectorId())
                .addValue("createdTs", new Date()), Long.class);
    }

    public Long addSessionDetails(SessionData sessionData) {
        String sql = "INSERT INTO session_details(uid, created_ts, name, agreement)" +
                " VALUES (:uid, :createdTs, :name, :agreement) RETURNING id" ;

        return namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource()
                .addValue("uid", sessionData.getUid())
                .addValue("createdTs", new Date())
                .addValue("name", sessionData.getName())
                .addValue("agreement", sessionData.getAgreement()), Long.class);
    }
}
