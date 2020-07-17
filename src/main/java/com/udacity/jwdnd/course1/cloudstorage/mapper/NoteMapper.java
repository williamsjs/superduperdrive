package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends UserJoinMapper<Note> {

    String getById = "SELECT * FROM NOTES WHERE noteid = #{noteId}";

    String getByUserId = "SELECT * FROM NOTES WHERE userid = #{userId}";

    String insert = "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})";

    String update = "UPDATE NOTES SET notetitle = #{noteTitle}, notedescription=#{noteDescription} WHERE noteid = #{noteId}";

    String delete = "DELETE FROM NOTES WHERE noteid = #{noteId}";

    @Select(getById)
    Note getById(int id);

    @Select(getByUserId)
    List<Note> getByUserId(int userId);

    @Insert(insert)
    @Options(useGeneratedKeys = true, keyProperty="noteId", keyColumn="noteid")
    int insert(Note note);

    @Update(update)
    void update(Note note);

    @Delete(delete)
    void delete(int id);

}


