package com.example.javaPracticalTest.service;

import com.example.javaPracticalTest.dto.UsersDto;
import java.util.List;

public interface UsersService {

  UsersDto createUsers(UsersDto usersDto);
  UsersDto updateUsers(UsersDto usersDto, Long usersId);
  UsersDto deleteUsers(Long usersId);
  List<UsersDto> getUsersByBirthDate(String birthDateFrom, String birthDateTo);
}
