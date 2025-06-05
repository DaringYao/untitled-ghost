package com.ghost.system.mapper;

import com.ghost.system.entity.GhostRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostRoleMapper {

    @Select("SELECT * FROM ghost_role WHERE id = #{id}")
    GhostRole selectById(Long id);

    @Select("SELECT * FROM ghost_role")
    List<GhostRole> selectAll();

    @Insert({
        "INSERT INTO ghost_role(name, code, description, created_at, updated_at)",
        "VALUES(#{name}, #{code}, #{description}, now(), now())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostRole role);

    @Update({
        "UPDATE ghost_role SET name=#{name}, code=#{code}, description=#{description},",
        "updated_at=now() WHERE id=#{id}"
    })
    void update(GhostRole role);

    @Delete("DELETE FROM ghost_role WHERE id = #{id}")
    void deleteById(Long id);
}