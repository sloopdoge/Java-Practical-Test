package com.example.javaPracticalTest.api;

import com.example.javaPracticalTest.api.constant.ResourceConstant;
import com.example.javaPracticalTest.config.ValidationGroupMarker;
import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceConstant.USER_BASE_URL)
@RequiredArgsConstructor
public class UsersController {
  @Autowired
  private final UsersService service;

  @Operation(summary = "Create users")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping()
  public UsersDto createUsers(@RequestBody @Validated(ValidationGroupMarker.Create.class) final UsersDto userDto) {
    return service.createUsers(userDto);
  }

  @Operation(summary = "Update users")
  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/{user-id}")
  public UsersDto updateUsers(@RequestBody @Valid final UsersDto usersDto, @PathVariable("user-id") final Long userId) {
    return service.updateUsers(usersDto, userId);
  }

  @Operation(summary = "Delete users")
  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/{user-id}")
  public UsersDto deleteUsers(@PathVariable("user-id") final Long userId) {
    return service.deleteUsers(userId);
  }

  @Operation(summary = "Get users by their birth date")
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{birth-date-from}/{birth-date-to}")
  public List<UsersDto> getUsersByBirthDate(@PathVariable("birth-date-from") String birthDateFrom, @PathVariable("birth-date-to") String birthDateTo) {
    return service.getUsersByBirthDate(birthDateFrom, birthDateTo);
  }
}
