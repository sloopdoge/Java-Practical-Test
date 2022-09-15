package com.example.javaPracticalTest.repository;

import com.example.javaPracticalTest.entity.Users;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
  List<Users> findAllByBirthDateBetween(Date birthDateFrom, Date birthDateTo);
}
