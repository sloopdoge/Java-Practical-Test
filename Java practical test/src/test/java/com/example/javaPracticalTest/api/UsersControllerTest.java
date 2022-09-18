package com.example.javaPracticalTest.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.javaPracticalTest.config.Config;
import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.entity.Users;
import com.example.javaPracticalTest.exception.EntityDoesNotExistException;
import com.example.javaPracticalTest.exception.IncorrectFormatDateException;
import com.example.javaPracticalTest.exception.IncorrectInputDateException;
import com.example.javaPracticalTest.exception.IncorrectUsersAgeException;
import com.example.javaPracticalTest.mapper.UsersMapper;
import com.example.javaPracticalTest.mapper.impl.UsersMapperImpl;
import com.example.javaPracticalTest.repository.UsersRepository;
import com.example.javaPracticalTest.service.UsersService;
import com.example.javaPracticalTest.service.impl.UsersServiceImpl;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ContextConfiguration(classes = UsersServiceImpl.class)
@TestPropertySource
class UsersControllerTest {

  @Mock
  Config config;

  @Mock
  private UsersRepository repository;

  @Spy
  private UsersMapper mapper = new UsersMapperImpl();

  @InjectMocks
  private UsersServiceImpl service;

  @Test
  void createUsers() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();
    var expected = Users.builder()
        .id(1L)
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();
    when(repository.save(any())).thenReturn(expected);
    when(config.getUsersAge()).thenReturn("18");
    var actual = service.createUsers(usersDto);


    assertEquals(expected.getId(), actual.getId());
    assertEquals(expected.getFirstName(), actual.getFirstName());
    assertEquals(expected.getLastName(), actual.getLastName());
    assertEquals(expected.getEmail(), actual.getEmail());
    assertEquals(expected.getAddress(), actual.getAddress());
    assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
  }

  @Test
  public void createUsers_shouldThrowIncorrectUserAgeException() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2010-01-01"))
        .phoneNumber("+44444444")
        .build();
    when(config.getUsersAge()).thenReturn("18");

    assertThrows(IncorrectUsersAgeException.class, () -> service.createUsers(usersDto));
  }

  @Test
  public void createUsers_shouldThrowIncorrectInputDateException() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2022-09-20"))
        .phoneNumber("+44444444")
        .build();
    when(config.getUsersAge()).thenReturn("18");

    assertThrows(IncorrectInputDateException.class, () -> service.createUsers(usersDto));
  }

  @Test
  public void updateUsers() {

    var userDataBase = Users.builder()
        .id(1L)
        .firstName("Vladislav")
        .lastName("Slupkovik")
        .email("w@w.w")
        .address("Miami Beach")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444475")
        .build();

    var usersDto = UsersDto.builder()
        .id(1L)
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();


    when(repository.findById(any())).thenReturn(Optional.of(userDataBase));

    var result = service.updateUsers(usersDto, 1L);

    var updatedUsersDto = UsersDto.builder()
        .id(1L)
        .firstName("Vlad")
        .lastName("Slupko")
        .email("w@w.w")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();

    assertEquals(updatedUsersDto.getId(), result.getId());
    assertEquals(updatedUsersDto.getFirstName(), result.getFirstName());
    assertEquals(updatedUsersDto.getLastName(), result.getLastName());
    assertEquals(updatedUsersDto.getEmail(), result.getEmail());
    assertEquals(updatedUsersDto.getAddress(), result.getAddress());
    assertEquals(updatedUsersDto.getPhoneNumber(), result.getPhoneNumber());
  }


  @Test
  public void deleteUsers() {
    var userDataBase = Users.builder()
        .id(1L)
        .firstName("Vladislav")
        .lastName("Slupkovik")
        .email("w@w.w")
        .address("Miami Beach")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444475")
        .build();
    when(repository.findById(any())).thenReturn(Optional.of(userDataBase));
    doNothing().when(repository).delete(userDataBase);
    var result = service.deleteUsers(1L);
    assertEquals(userDataBase.getId(), result.getId());
    assertEquals(userDataBase.getFirstName(), result.getFirstName());
    assertEquals(userDataBase.getLastName(), result.getLastName());
    assertEquals(userDataBase.getEmail(), result.getEmail());
    assertEquals(userDataBase.getAddress(), result.getAddress());
    assertEquals(userDataBase.getPhoneNumber(), result.getPhoneNumber());
  }

  @Test
  public void deleteUsers_shouldThrowEntityDoesNotExist() {
    when(repository.findById(any())).thenReturn(Optional.empty());
    assertThrows(EntityDoesNotExistException.class, () -> service.deleteUsers(1L));
  }

  @Test
  public void getUsersByBirthDate() {
    var listUsers = List.of(Users.builder().id(1L)
        .firstName("Vladislav")
        .lastName("Slupkovik")
        .email("w@w.w")
        .address("Miami Beach")
        .birthDate(Date.valueOf("2017-01-01"))
        .phoneNumber("+44444475")
        .build());
    var expected = List.of(UsersDto.builder().id(1L)
        .firstName("Vladislav")
        .lastName("Slupkovik")
        .email("w@w.w")
        .address("Miami Beach")
        .birthDate(Date.valueOf("2017-01-01"))
        .phoneNumber("+44444475")
        .build());
    when(repository.findAllByBirthDateBetween(any(), any())).thenReturn(listUsers);
    var result = service.getUsersByBirthDate("2015-07-07", "2020-07-07");
    assertEquals(expected.get(0).getId(), result.get(0).getId());
    assertEquals(expected.get(0).getFirstName(), result.get(0).getFirstName());
    assertEquals(expected.get(0).getLastName(), result.get(0).getLastName());
  }
  @Test
  public void getUsersByBirthDate_shouldThrowIncorrectFormatDateException() {
    assertThrows(IncorrectFormatDateException.class, () -> service.getUsersByBirthDate("07/07/2015","2020/07/07"));
  }
}