package com.example.weblab4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double x;
    private double y;
    private double r;
    private double time;
    private boolean result;
    //login

    @Column(updatable = false, name = "creation_date")
    private LocalDateTime creationDate;
}
