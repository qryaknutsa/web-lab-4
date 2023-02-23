package com.example.weblab4.controller;

import com.example.weblab4.entity.Point;
import com.example.weblab4.repo.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/point")
@CrossOrigin(origins = "http://localhost:8081")
public class PointController {
    private final PointRepo pointRepo;

    @Autowired
    public PointController(PointRepo pointRepo) {
        this.pointRepo = pointRepo;
    }

    @GetMapping
    public List<Point> list(){
        return pointRepo.findAll();
    }

    @GetMapping("{id}")
    public Point getOne(@PathVariable("id") Point point){
        return point;
    }

    @PostMapping
    public Point create(@RequestBody Point point){
        point.setCreationDate(LocalDateTime.now());
        return pointRepo.save(point);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Point point){
        pointRepo.delete(point);
    }

}
