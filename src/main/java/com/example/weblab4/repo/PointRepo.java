package com.example.weblab4.repo;

import com.example.weblab4.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepo extends JpaRepository<Point, Long> {
    List<Point> findByLogin(String str);
}
