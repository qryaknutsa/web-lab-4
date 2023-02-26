package com.example.weblab4.repo;

import com.example.weblab4.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<MyUser, String> {
}
