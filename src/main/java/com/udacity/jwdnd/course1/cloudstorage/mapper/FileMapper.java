package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper extends UserJoinMapper<File> {

    String getById = "SELECT * FROM FILES WHERE fileid = #{fileId}";

    String getByUserId = "SELECT * FROM FILES WHERE userid = #{userId}";

    String insert = "INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES (#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})";

    String delete = "DELETE FROM FILES WHERE fileid = #{fileId}";

    @Select(getById)
    File getById(int fileId);

    @Select(getByUserId)
    List<File> getByUserId(int userId);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty="fileId", keyColumn = "fileid")
    int insert(File file);

    @Delete(delete)
    void delete(int fileId);

}
