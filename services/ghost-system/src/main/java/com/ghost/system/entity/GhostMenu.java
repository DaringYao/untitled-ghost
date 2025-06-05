package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GhostMenu {
    private Long id; // 菜单ID

    private Long parentId; // 父菜单ID

    private String name; // 菜单名称

    private String code; // 菜单编码（用于权限标识）

    private String path; // 路由路径（前端或API路径）

    private String component; // 前端组件路径（如 layouts/Dashboard.vue）

    private String icon; // 菜单图标（如 fa-dashboard）

    private Integer sort; // 排序字段

    private String menuType; // 菜单类型：M-目录；C-菜单；A-按钮/操作

    private Integer status; // 菜单状态：1-启用；0-禁用

    private Boolean visible; // 是否在菜单栏中可见

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private List<GhostMenu> children; // 子菜单列表
    private List<GhostRole> roles;    // 拥有该菜单的角色列表
}