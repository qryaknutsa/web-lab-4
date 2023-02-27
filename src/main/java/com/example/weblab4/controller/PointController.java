package com.example.weblab4.controller;

import com.example.weblab4.entity.Point;
import com.example.weblab4.repo.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/point")
@CrossOrigin
public class PointController {
    private final PointRepo pointRepo;

    @Autowired
    public PointController(PointRepo pointRepo) {
        this.pointRepo = pointRepo;
    }

//    @GetMapping
//    public List<Point> list(){
//        return pointRepo.findAll();
//    }
    @GetMapping("{login}")
    public List<Point> findByLogin(@PathVariable("login") String login){
        return pointRepo.findByLogin(login);
    }


    @PostMapping
    public Point create(@RequestBody Point point){
        double currentTime = System.nanoTime();

        System.out.println(point.getLogin());
        point.setCreationDate(LocalDateTime.now());
        point.setResult(checkArea(point));
        point.setTime((System.nanoTime() - currentTime) / 1e6);
        return pointRepo.save(point);
    }
    @DeleteMapping()
    public void delete(){
        pointRepo.deleteAll();
    }


    public boolean checkArea(Point point){
        if(point.getX() >= 0 && point.getY() >= 0){
            return point.getX() <= point.getR() && point.getY() <= point.getR() / 2;
        }
        else if(point.getX() <= 0 && point.getY() >= 0){
            return point.getX()*point.getX() + point.getY()*point.getY() <= point.getR()*point.getR();
        }
        else if(point.getX() >= 0 && point.getY() <= 0){
            return point.getY() >= 2*point.getX() - point.getR();
        }
        else return false;
    }


}
