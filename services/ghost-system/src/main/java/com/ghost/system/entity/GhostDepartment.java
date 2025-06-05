package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GhostDepartment {
    private Long id; // 部门ID

    private String name; // 部门名称

    private String code; // 部门编码（唯一）

    private Long parentId; // 父级部门ID

    private String description; // 部门描述

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private List<GhostPosition> positions; // 部门下的岗位列表
    private List<GhostEmployee> employees; // 部门下的员工列表
}