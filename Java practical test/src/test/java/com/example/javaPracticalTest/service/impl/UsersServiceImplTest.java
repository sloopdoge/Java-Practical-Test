package com.example.javaPracticalTest.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.javaPracticalTest.config.Config;
import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.entity.Users;
import com.example.javaPracticalTest.exception.EntityDoesNotExistException;
import com.example.javaPracticalTest.exception.IncorrectInputDateException;
import com.example.javaPracticalTest.exception.IncorrectUserAgeException;
import com.example.javaPracticalTest.mapper.UsersMapper;
import com.example.javaPracticalTest.mapper.impl.UsersMapperImpl;
import com.example.javaPracticalTest.repository.UsersRepository;
import java.sql.Date;
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
public class UsersServiceImplTest {
  @Mock
  Config config;

  @Mock
  private UsersRepository repository;
  @Spy
  private UsersMapper mapper = new UsersMapperImpl();
  @InjectMocks
  private UsersServiceImpl service;

  @Test
  public void createUsers() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();
    var expected = Users.builder()
        .id(1L)
        .firstName("Vlad")
        .lastName("Slupko")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();
    when(repository.save(any())).thenReturn(expected);
    when(config.getUsersAge()).thenReturn("18");
    var actual = service.createUser(usersDto);


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
        .address("Washington DC")
        .birthDate(Date.valueOf("2010-01-01"))
        .phoneNumber("+44444444")
        .build();
    when(config.getUsersAge()).thenReturn("18");

    assertThrows(IncorrectUserAgeException.class, () -> service.createUser(usersDto));
  }

  @Test
  public void createUsers_shouldThrowIncorrectInputDateException() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .address("Washington DC")
        .birthDate(Date.valueOf("2022-09-20"))
        .phoneNumber("+44444444")
        .build();
    when(config.getUsersAge()).thenReturn("18");

    assertThrows(IncorrectInputDateException.class, () -> service.createUser(usersDto));
  }

  @Test
  public void updateUser() {
    var usersDto = UsersDto.builder()
        .firstName("Vlad")
        .lastName("Slupko")
        .address("Washington DC")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+44444444")
        .build();

    var expected = Users.builder()
        .id(1L)
        .firstName("Vladislav")
        .lastName("Slupko")
        .address("Washington DC 1")
        .birthDate(Date.valueOf("2001-01-01"))
        .phoneNumber("+555555")
        .build();
    when(repository.save(any())).thenReturn(expected);
    when(config.getUsersAge()).thenReturn("18");

    assertThrows(EntityDoesNotExistException.class, () -> service.updateUser(usersDto, 2L));
  }
}