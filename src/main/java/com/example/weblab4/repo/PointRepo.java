package com.example.weblab4.repo;

import com.example.weblab4.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepo extends JpaRepository<Point, Long> {
    List<Point> findByLogin(String str);

    @Modifying
    @Query(value = "delete from point where login = :login", nativeQuery = true)
    void deleteAllByLogin(@Param("login") String login);
}
