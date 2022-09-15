package com.example.javaPracticalTest.dto;

import com.example.javaPracticalTest.config.ValidationGroupMarker.Create;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Date;
import java.time.Instant;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Accessors(chain = true)
@Builder
@JsonDeserialize(builder = UsersDto.UsersDtoBuilder.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersDto {

  @Null(groups = Default.class)
  @JsonProperty("id")
  private Long id;

  @NotNull(groups = Create.class)
  @JsonProperty("first_name")
  private String firstName;

  @NotNull(groups = Create.class)
  @JsonProperty("last_name")
  private String lastName;

  @NotNull(groups = Create.class)
  @JsonProperty("email")
  @Email(regexp = "^([a-z0-9]+)@([a-z]+).([a-z]+)")
  private String email;


  @NotNull(groups = Create.class)
  @JsonProperty("birth_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
//  @Temporal(TemporalType.DATE)
  @JsonFormat(pattern = "yyyy-MM-dd")
  Date birthDate;

  @JsonProperty("address")
  private String address;

  @JsonProperty("phone_number")
  private String phoneNumber;
}
