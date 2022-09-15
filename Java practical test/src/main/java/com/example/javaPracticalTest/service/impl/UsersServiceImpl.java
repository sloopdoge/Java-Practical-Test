package com.example.javaPracticalTest.service.impl;

import com.example.javaPracticalTest.config.Config;
import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.exception.EntityDoesNotExistException;
import com.example.javaPracticalTest.exception.IncorrectFormatDateException;
import com.example.javaPracticalTest.exception.IncorrectInputDateException;
import com.example.javaPracticalTest.exception.IncorrectUserAgeException;
import com.example.javaPracticalTest.mapper.UsersMapper;
import com.example.javaPracticalTest.repository.UsersRepository;
import com.example.javaPracticalTest.service.UsersService;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//@PropertySource("users-info.properties")
public class UsersServiceImpl implements UsersService {

  final UsersRepository repository;
  final UsersMapper mapper;

//  @Value("${users.age}")
//  final String age;
  final Config config;

  @Override
  public UsersDto createUser(UsersDto usersDto) {
    correctBirthDate(usersDto.getBirthDate());
    aboveAge(usersDto.getBirthDate());
    return mapper.convert(repository.save(mapper.convert(usersDto)));
  }

  @Transactional
  @Override
  public UsersDto updateUser(UsersDto usersDto, Long usersId) {
    var currentUser = repository.findById(usersId);

    if (!currentUser.isPresent()) {
      throw new EntityDoesNotExistException("User does not exist");
    }
    correctBirthDate(usersDto.getBirthDate());

    var receivedUser = mapper.convert(usersDto);
    var updatedUser = mapper.updateEntity(receivedUser, currentUser.get());

    return mapper.convert(updatedUser);
  }

  @Override
  public UsersDto deleteUser(Long usersId) {
    var user = repository.findById(usersId);

    if (user.isPresent()) {
      throw new EntityDoesNotExistException("User does not exist");
    }

    repository.delete(user.get());

    return mapper.convert(user.get());
  }

  @Override
  public List<UsersDto> getByBirthDate(String birthDateFrom, String birthDateTo) {

    var dateFrom = convertToDate(birthDateFrom);
    var dateTo = convertToDate(birthDateTo);
    var user = repository.findAllByBirthDateBetween(dateFrom, dateTo);

    if (!Objects.nonNull(user)) {
      throw new EntityDoesNotExistException("User does not exist");
    }

    return user.stream().map(users ->
        mapper.convert(users)).toList();
  }

  private Date convertToDate(String date){
    try {
      var dateFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
      var sqlDate = new java.sql.Date(dateFormat.getTime());
      return sqlDate;
    } catch (ParseException e) {
      throw new IncorrectFormatDateException("Incorrect data input");
    }
  }

  private String convertToString(Date date) {
    try {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      return dateFormat.format(date);
    } catch (DateTimeParseException e) {
      throw new IncorrectFormatDateException("Incorrect data input");
    }
  }

  private void correctBirthDate(Date birthDate) {
    String localDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    Date localDate = convertToDate(localDateTime);

    if (Period.between(birthDate.toLocalDate(), localDate.toLocalDate()).isNegative() ||
        Period.between(birthDate.toLocalDate(), localDate.toLocalDate()).isZero()) {
      throw new IncorrectInputDateException("Incorrect date: value must be earlier than current date");
    }
  }

  private void aboveAge(Date birthDate) {
    String localDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
    Date localDate = convertToDate(localDateTime);
    if (calculateAge(birthDate, localDate) < Long.parseLong(config.getUsersAge())) {
      throw new IncorrectUserAgeException("User under " + config.getUsersAge());
    }
  }

  private int calculateAge(Date birthDate, Date localDate) {
    return Period.between(birthDate.toLocalDate(), localDate.toLocalDate()).getYears();
  }

//  private Instant convertToInstant(String date) {
//    try {
//      return stringToInstant("yyyy-MM-dd", date);
//    } catch (DateTimeParseException e) {
//      throw new IncorrectFormatDateException("Incorrect data input");
//    }
//  }
//
//  private static Instant stringToInstant(String dateFormat, String dateString) {
//    var date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
//    return date.atStartOfDay()
//        .toInstant(ZoneOffset.UTC);
//  }
}
