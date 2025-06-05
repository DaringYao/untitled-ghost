package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class GhostEmployee {
    private Long id; // 员工ID

    private Long userId; // 关联的用户ID

    private String name; // 员工姓名

    private String gender; // 性别

    private String mobile; // 手机号码

    private String email; // 电子邮箱

    private LocalDate entryDate; // 入职日期

    private LocalDate leaveDate; // 离职日期（为空表示在职）

    private Long positionId; // 当前岗位ID

    private Long departmentId; // 当前部门ID

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private GhostUser user;      // 对应的用户
    private GhostPosition position; // 当前岗位
    private GhostDepartment department; // 当前部门
}