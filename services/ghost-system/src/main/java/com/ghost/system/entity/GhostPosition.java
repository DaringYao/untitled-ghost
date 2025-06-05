package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GhostPosition {
    private Long id; // 岗位ID

    private String name; // 岗位名称

    private String code; // 岗位编码

    private Long departmentId; // 所属部门ID

    private String description; // 岗位描述

    private Boolean isActive; // 是否启用

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private GhostDepartment department; // 所属部门
    private List<GhostEmployee> employees; // 岗位上的员工列表
    private List<GhostRole> roles; // 岗位绑定的角色列表（可选）
}
