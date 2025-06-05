package com.ghost.system.mapper;

import com.ghost.system.entity.GhostEmployee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GhostEmployeeMapper {

    @Select("SELECT * FROM ghost_employee WHERE id = #{id}")
    GhostEmployee selectById(Long id);

    @Select("SELECT * FROM ghost_employee")
    List<GhostEmployee> selectAll();

    @Insert({
        "INSERT INTO ghost_employee(user_id, name, gender, mobile, email, entry_date, leave_date, position_id, department_id, created_at, updated_at)",
        "VALUES(#{userId}, #{name}, #{gender}, #{mobile}, #{email}, #{entryDate}, #{leaveDate}, #{positionId}, #{departmentId}, now(), now())"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(GhostEmployee employee);

    @Update({
        "UPDATE ghost_employee SET user_id=#{userId}, name=#{name}, gender=#{gender}, mobile=#{mobile}, email=#{email},",
        "entry_date=#{entryDate}, leave_date=#{leaveDate}, position_id=#{positionId}, department_id=#{departmentId},",
        "updated_at=now() WHERE id=#{id}"
    })
    void update(GhostEmployee employee);

    @Delete("DELETE FROM ghost_employee WHERE id = #{id}")
    void deleteById(Long id);
}