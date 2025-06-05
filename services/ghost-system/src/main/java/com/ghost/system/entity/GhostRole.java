package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GhostRole {
    private Long id; // 角色ID

    private String name; // 角色名称

    private String code; // 角色编码（唯一）

    private String description; // 角色描述

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private List<GhostMenu> menus; // 角色拥有的菜单列表
}