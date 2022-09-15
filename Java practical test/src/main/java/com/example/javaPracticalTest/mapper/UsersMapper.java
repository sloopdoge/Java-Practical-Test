package com.example.javaPracticalTest.mapper;

import com.example.javaPracticalTest.dto.UsersDto;
import com.example.javaPracticalTest.entity.Users;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = UsersConfigMapper.class)
public interface UsersMapper {

  Users convert(UsersDto usersDto);

  UsersDto convert(Users users);

  @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mappings({@Mapping(target = "id", ignore = true)})
  Users updateEntity(final Users source, @MappingTarget Users target);
}
