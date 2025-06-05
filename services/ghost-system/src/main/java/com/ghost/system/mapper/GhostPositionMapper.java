package com.ghost.system.mapper;

import com.ghost.system.entity.GhostPosition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostPositionMapper {

    @Select("SELECT * FROM ghost_position WHERE id = #{id}")
    GhostPosition selectById(Long id);

    @Select("SELECT * FROM ghost_position")
    List<GhostPosition> selectAll();

    @Insert({
        "INSERT INTO ghost_position(name, code, department_id, description, is_active, created_at, updated_at)",
        "VALUES(#{name}, #{code}, #{departmentId}, #{description}, #{isActive}, now(), now())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostPosition position);

    @Update({
        "UPDATE ghost_position SET name=#{name}, code=#{code}, department_id=#{departmentId}, description=#{description},",
        "is_active=#{isActive}, updated_at=now() WHERE id=#{id}"
    })
    void update(GhostPosition position);

    @Delete("DELETE FROM ghost_position WHERE id = #{id}")
    void deleteById(Long id);
}