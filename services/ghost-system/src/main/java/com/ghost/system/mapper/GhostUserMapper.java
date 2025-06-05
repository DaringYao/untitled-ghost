package com.ghost.system.mapper;

import com.ghost.system.entity.GhostUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostUserMapper {

    @Select("SELECT * FROM ghost_user WHERE id = #{id}")
    GhostUser selectById(Long id);

    @Select("SELECT * FROM ghost_user")
    List<GhostUser> selectAll();

    @Insert("INSERT INTO ghost_user(username, password, email, phone, status, created_at, updated_at) " +
            "VALUES(#{username}, #{password}, #{email}, #{phone}, #{status}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostUser user);

    @Update("UPDATE ghost_user SET username=#{username}, password=#{password}, email=#{email}, phone=#{phone}, " +
            "status=#{status}, updated_at=now() WHERE id=#{id}")
    void update(GhostUser user);

    @Delete("DELETE FROM ghost_user WHERE id = #{id}")
    void delete(Long id);
}