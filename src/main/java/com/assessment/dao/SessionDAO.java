package com.assessment.dao;

import com.assessment.api.Session;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Session.class)
public interface SessionDAO {
    @SqlUpdate("INSERT INTO sessions (title, description, speaker_name, file_url) VALUES (:title, :description, :speakerName, :fileUrl)")
    @GetGeneratedKeys
    int insert(@BindBean Session session);

    @SqlQuery("SELECT * FROM sessions WHERE id = :id")
    Optional<Session> findById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM sessions")
    List<Session> findAll();

    @SqlUpdate("UPDATE sessions SET title = :title, description = :description, speaker_name = :speakerName, file_url = :fileUrl WHERE id = :id")
    void update(@Bind("id") int id, @BindBean Session session);

    @SqlUpdate("DELETE FROM sessions WHERE id = :id")
    void delete(@Bind("id") int id);
}