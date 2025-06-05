package com.ghost.system.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GhostUser {
    private Long id; // 用户ID

    private String username; // 登录用户名

    private String password; // 登录密码（加密存储）

    private String email; // 用户邮箱

    private String phone; // 联系电话

    private Integer status; // 用户状态：1-启用；0-禁用

    private LocalDateTime createdAt; // 创建时间

    private LocalDateTime updatedAt; // 最后更新时间

    // 可选关联字段
    private GhostEmployee employee; // 用户对应的员工信息
    private List<GhostRole> roles;  // 用户拥有的角色列表
}