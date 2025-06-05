package com.ghost.system.mapper;

import com.ghost.system.entity.GhostDepartment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostDepartmentMapper {

    @Select("SELECT * FROM ghost_department WHERE id = #{id}")
    GhostDepartment selectById(Long id);

    @Select("SELECT * FROM ghost_department")
    List<GhostDepartment> selectAll();

    @Insert({
        "INSERT INTO ghost_department(name, code, parent_id, description, created_at, updated_at)",
        "VALUES(#{name}, #{code}, #{parentId}, #{description}, now(), now())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostDepartment department);

    @Update({
        "UPDATE ghost_department SET name=#{name}, code=#{code}, parent_id=#{parentId}, description=#{description},",
        "updated_at=now() WHERE id=#{id}"
    })
    void update(GhostDepartment department);

    @Delete("DELETE FROM ghost_department WHERE id = #{id}")
    void deleteById(Long id);
}