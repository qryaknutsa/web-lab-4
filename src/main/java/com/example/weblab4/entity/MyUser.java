package com.example.weblab4.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    @Id
    private String login;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MyUser myUserR = (MyUser) o;
        return login != null && Objects.equals(login, myUserR.login);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
