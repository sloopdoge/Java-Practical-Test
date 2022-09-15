package com.example.javaPracticalTest.service;

import com.example.javaPracticalTest.dto.UsersDto;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface UsersService {

  UsersDto createUser(UsersDto usersDto);
  UsersDto updateUser(UsersDto usersDto, Long usersId);
  UsersDto deleteUser(Long usersId);
  List<UsersDto> getByBirthDate(String birthDateFrom, String birthDateTo);
}
