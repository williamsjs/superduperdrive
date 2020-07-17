package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends UserJoinMapper<Credential> {

    String getById = "SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}";

    String getByUserId = "SELECT * FROM CREDENTIALS WHERE userid = #{userId}";

    String insert = "INSERT INTO CREDENTIALS (url, username, password, userid) VALUES (#{url}, #{username}, #{password}, #{userId})";

    String update = "UPDATE CREDENTIALS SET url=#{url}, username=#{username}, password=#{password} WHERE credentialid = #{credentialId}";

    String delete = "DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}";

    @Select(getById)
    Credential getById(int credentialId);

    @Select(getByUserId)
    List<Credential> getByUserId(int userId);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty = "credentialId", keyColumn = "credentialid")
    int insert(Credential credential);

    @Update(update)
    void update(Credential credential);

    @Delete(delete)
    void delete(int credentialId);

}
