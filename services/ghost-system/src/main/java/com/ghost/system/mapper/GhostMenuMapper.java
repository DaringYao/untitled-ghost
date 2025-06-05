package com.ghost.system.mapper;


import com.ghost.system.entity.GhostMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostMenuMapper {

    @Select("SELECT * FROM ghost_menu WHERE id = #{id}")
    GhostMenu selectById(Long id);

    @Select("SELECT * FROM ghost_menu")
    List<GhostMenu> selectAll();

    @Insert({
        "INSERT INTO ghost_menu(parent_id, name, code, path, component, icon, sort, menu_type, status, visible, created_at, updated_at)",
        "VALUES(#{parentId}, #{name}, #{code}, #{path}, #{component}, #{icon}, #{sort}, #{menuType}, #{status}, #{visible}, now(), now())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostMenu menu);

    @Update({
        "UPDATE ghost_menu SET parent_id=#{parentId}, name=#{name}, code=#{code}, path=#{path}, component=#{component},",
        "icon=#{icon}, sort=#{sort}, menu_type=#{menuType}, status=#{status}, visible=#{visible}, updated_at=now()",
        "WHERE id=#{id}"
    })
    void update(GhostMenu menu);

    @Delete("DELETE FROM ghost_menu WHERE id = #{id}")
    void deleteById(Long id);
}