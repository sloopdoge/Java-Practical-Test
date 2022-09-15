package com.example.javaPracticalTest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Instance;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "Users")
public class Users {

  private static final String SEQ_NAME = "user_seq";

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
  @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
  @Column(name = "id", nullable = false, updatable = false)
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email")
  private String email;

  @Column(name = "birth_nate")
  @DateTimeFormat(pattern = "YYYY-MM-DD")
  @JsonFormat(pattern = "YYYY-MM-DD")
  Date birthDate;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  private String phoneNumber;

}
