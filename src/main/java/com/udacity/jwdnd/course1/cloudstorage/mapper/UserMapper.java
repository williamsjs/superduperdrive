package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    String getById = "SELECT * FROM USERS WHERE userid = #{int}";

    String getByUsername = "SELECT * FROM USERS WHERE username = #{username}";

    String insert = "INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})";

    @Select(getById)
    User getById(int id);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty="id")
    int insert(User user);

    @Select(getByUsername)
    User getByUsername(String username);

}
