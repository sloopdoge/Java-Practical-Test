package com.example.javaPracticalTest.mapper.impl;

import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.entity.Users;
import com.example.javaPracticalTest.mapper.UsersMapper;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-18T10:56:55+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public Users convert(UsersDto usersDto) {
        if ( usersDto == null ) {
            return null;
        }

        Users.UsersBuilder users = Users.builder();

        if ( usersDto.getId() != null ) {
            users.id( usersDto.getId() );
        }
        if ( usersDto.getFirstName() != null ) {
            users.firstName( usersDto.getFirstName() );
        }
        if ( usersDto.getLastName() != null ) {
            users.lastName( usersDto.getLastName() );
        }
        if ( usersDto.getEmail() != null ) {
            users.email( usersDto.getEmail() );
        }
        if ( usersDto.getBirthDate() != null ) {
            users.birthDate( usersDto.getBirthDate() );
        }
        if ( usersDto.getAddress() != null ) {
            users.address( usersDto.getAddress() );
        }
        if ( usersDto.getPhoneNumber() != null ) {
            users.phoneNumber( usersDto.getPhoneNumber() );
        }

        return users.build();
    }

    @Override
    public UsersDto convert(Users users) {
        if ( users == null ) {
            return null;
        }

        UsersDto.UsersDtoBuilder usersDto = UsersDto.builder();

        if ( users.getId() != null ) {
            usersDto.id( users.getId() );
        }
        if ( users.getFirstName() != null ) {
            usersDto.firstName( users.getFirstName() );
        }
        if ( users.getLastName() != null ) {
            usersDto.lastName( users.getLastName() );
        }
        if ( users.getEmail() != null ) {
            usersDto.email( users.getEmail() );
        }
        if ( users.getBirthDate() != null ) {
            usersDto.birthDate( users.getBirthDate() );
        }
        if ( users.getAddress() != null ) {
            usersDto.address( users.getAddress() );
        }
        if ( users.getPhoneNumber() != null ) {
            usersDto.phoneNumber( users.getPhoneNumber() );
        }

        return usersDto.build();
    }

    @Override
    public Users updateEntity(Users source, Users target) {
        if ( source == null ) {
            return target;
        }

        if ( source.getFirstName() != null ) {
            target.setFirstName( source.getFirstName() );
        }
        if ( source.getLastName() != null ) {
            target.setLastName( source.getLastName() );
        }
        if ( source.getEmail() != null ) {
            target.setEmail( source.getEmail() );
        }
        if ( source.getBirthDate() != null ) {
            target.setBirthDate( source.getBirthDate() );
        }
        if ( source.getAddress() != null ) {
            target.setAddress( source.getAddress() );
        }
        if ( source.getPhoneNumber() != null ) {
            target.setPhoneNumber( source.getPhoneNumber() );
        }

        return target;
    }
}
