-- 用户表：ghost_user
-- 存储系统登录用户的基本信息
CREATE TABLE ghost_user
(
    id         BIGSERIAL PRIMARY KEY,                              -- 用户唯一标识（主键）
    username   VARCHAR(50) NOT NULL UNIQUE,                        -- 登录用户名（唯一）
    password   TEXT        NOT NULL,                               -- 登录密码（建议加密存储）
    email      VARCHAR(100),                                       -- 邮箱地址
    phone      VARCHAR(20),                                        -- 联系电话
    status     SMALLINT                 DEFAULT 1,                 -- 用户状态：1-启用；0-禁用
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间（带时区）
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 最后更新时间（带时区）
);

COMMENT ON TABLE ghost_user IS '系统用户表';
COMMENT ON COLUMN ghost_user.id IS '用户ID';
COMMENT ON COLUMN ghost_user.username IS '登录用户名';
COMMENT ON COLUMN ghost_user.password IS '登录密码（加密存储）';
COMMENT ON COLUMN ghost_user.email IS '用户邮箱';
COMMENT ON COLUMN ghost_user.phone IS '联系电话';
COMMENT ON COLUMN ghost_user.status IS '用户状态：1-启用；0-禁用';
COMMENT ON COLUMN ghost_user.created_at IS '创建时间';
COMMENT ON COLUMN ghost_user.updated_at IS '最后更新时间';


-- 部门表：ghost_department
-- 存储组织架构中的部门信息（支持父子结构）
CREATE TABLE ghost_department
(
    id          BIGSERIAL PRIMARY KEY,                              -- 部门唯一标识（主键）
    name        VARCHAR(100) NOT NULL UNIQUE,                       -- 部门名称（唯一）
    code        VARCHAR(50) UNIQUE,                                 -- 部门编码（如 TECH, MKT）
    parent_id   BIGINT REFERENCES ghost_department (id),            -- 父级部门ID（用于树形结构）
    description TEXT,                                               -- 部门描述
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 最后更新时间
);

COMMENT ON TABLE ghost_department IS '部门信息表';
COMMENT ON COLUMN ghost_department.id IS '部门ID';
COMMENT ON COLUMN ghost_department.name IS '部门名称';
COMMENT ON COLUMN ghost_department.code IS '部门编码（唯一）';
COMMENT ON COLUMN ghost_department.parent_id IS '父级部门ID（自引用）';
COMMENT ON COLUMN ghost_department.description IS '部门描述';
COMMENT ON COLUMN ghost_department.created_at IS '创建时间';
COMMENT ON COLUMN ghost_department.updated_at IS '最后更新时间';


-- 岗位表：ghost_position
-- 存储部门下的具体岗位信息
CREATE TABLE ghost_position
(
    id            BIGSERIAL PRIMARY KEY,                                  -- 岗位唯一标识（主键）
    name          VARCHAR(100) NOT NULL,                                  -- 岗位名称
    code          VARCHAR(50),                                            -- 岗位编码（如 DEV, PM）
    department_id BIGINT       NOT NULL REFERENCES ghost_department (id), -- 所属部门ID
    description   TEXT,                                                   -- 岗位描述
    is_active     BOOLEAN                  DEFAULT TRUE,                  -- 是否启用：true-启用；false-停用
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,     -- 创建时间
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP      -- 最后更新时间
);

COMMENT ON TABLE ghost_position IS '岗位信息表';
COMMENT ON COLUMN ghost_position.id IS '岗位ID';
COMMENT ON COLUMN ghost_position.name IS '岗位名称';
COMMENT ON COLUMN ghost_position.code IS '岗位编码';
COMMENT ON COLUMN ghost_position.department_id IS '所属部门ID';
COMMENT ON COLUMN ghost_position.description IS '岗位描述';
COMMENT ON COLUMN ghost_position.is_active IS '是否启用';
COMMENT ON COLUMN ghost_position.created_at IS '创建时间';
COMMENT ON COLUMN ghost_position.updated_at IS '最后更新时间';


-- 员工表：ghost_employee
-- 存储员工信息，可与 ghost_user 表关联
CREATE TABLE ghost_employee
(
    id            BIGSERIAL PRIMARY KEY,                                      -- 员工唯一标识（主键）
    user_id       BIGINT       REFERENCES ghost_user (id) ON DELETE SET NULL, -- 关联的登录用户ID（可为空）
    name          VARCHAR(100) NOT NULL,                                      -- 员工姓名
    gender        VARCHAR(10),                                                -- 性别（如男/女）
    mobile        VARCHAR(20),                                                -- 手机号码
    email         VARCHAR(100),                                               -- 邮箱地址
    entry_date    DATE,                                                       -- 入职日期
    leave_date    DATE,                                                       -- 离职日期（空表示在职）
    position_id   BIGINT REFERENCES ghost_position (id),                      -- 当前岗位ID
    department_id BIGINT REFERENCES ghost_department (id),                    -- 当前部门ID
    created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,         -- 创建时间
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP          -- 最后更新时间
);

COMMENT ON TABLE ghost_employee IS '员工信息表';
COMMENT ON COLUMN ghost_employee.id IS '员工ID';
COMMENT ON COLUMN ghost_employee.user_id IS '关联的用户ID（可为空）';
COMMENT ON COLUMN ghost_employee.name IS '员工姓名';
COMMENT ON COLUMN ghost_employee.gender IS '性别';
COMMENT ON COLUMN ghost_employee.mobile IS '手机号码';
COMMENT ON COLUMN ghost_employee.email IS '电子邮箱';
COMMENT ON COLUMN ghost_employee.entry_date IS '入职日期';
COMMENT ON COLUMN ghost_employee.leave_date IS '离职日期（为空表示在职）';
COMMENT ON COLUMN ghost_employee.position_id IS '当前岗位ID';
COMMENT ON COLUMN ghost_employee.department_id IS '当前部门ID';
COMMENT ON COLUMN ghost_employee.created_at IS '创建时间';
COMMENT ON COLUMN ghost_employee.updated_at IS '最后更新时间';


-- 角色表：ghost_role
-- 存储角色信息（用于权限控制）
CREATE TABLE ghost_role
(
    id          BIGSERIAL PRIMARY KEY,                              -- 角色唯一标识（主键）
    name        VARCHAR(50) NOT NULL UNIQUE,                        -- 角色名称（唯一）
    code        VARCHAR(50) UNIQUE,                                 -- 角色编码（如 ADMIN, USER）
    description TEXT,                                               -- 角色描述
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP, -- 创建时间
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP  -- 最后更新时间
);

COMMENT ON TABLE ghost_role IS '角色信息表';
COMMENT ON COLUMN ghost_role.id IS '角色ID';
COMMENT ON COLUMN ghost_role.name IS '角色名称';
COMMENT ON COLUMN ghost_role.code IS '角色编码（唯一）';
COMMENT ON COLUMN ghost_role.description IS '角色描述';
COMMENT ON COLUMN ghost_role.created_at IS '创建时间';
COMMENT ON COLUMN ghost_role.updated_at IS '最后更新时间';


-- 用户-角色关联表：ghost_user_role
-- 存储用户与角色之间的多对多关系
CREATE TABLE ghost_user_role
(
    user_id BIGINT NOT NULL REFERENCES ghost_user (id) ON DELETE CASCADE, -- 用户ID
    role_id BIGINT NOT NULL REFERENCES ghost_role (id) ON DELETE CASCADE, -- 角色ID
    PRIMARY KEY (user_id, role_id)                                        -- 联合主键
);

COMMENT ON TABLE ghost_user_role IS '用户与角色关联表';
COMMENT ON COLUMN ghost_user_role.user_id IS '用户ID';
COMMENT ON COLUMN ghost_user_role.role_id IS '角色ID';


-- 岗位-角色关联表：ghost_position_role（可选）
-- 存储岗位与角色之间的多对多关系（用于自动绑定角色）
CREATE TABLE ghost_position_role
(
    position_id BIGINT NOT NULL REFERENCES ghost_position (id) ON DELETE CASCADE, -- 岗位ID
    role_id     BIGINT NOT NULL REFERENCES ghost_role (id) ON DELETE CASCADE,     -- 角色ID
    PRIMARY KEY (position_id, role_id)                                            -- 联合主键
);

COMMENT ON TABLE ghost_position_role IS '岗位与角色关联表（可选）';
COMMENT ON COLUMN ghost_position_role.position_id IS '岗位ID';
COMMENT ON COLUMN ghost_position_role.role_id IS '角色ID';

-- 菜单表：ghost_menu
-- 存储系统菜单信息，支持多级菜单结构
CREATE TABLE ghost_menu
(
    id         BIGSERIAL PRIMARY KEY,                                            -- 菜单唯一标识（主键）
    parent_id  BIGINT                   DEFAULT NULL REFERENCES ghost_menu (id), -- 父菜单ID（自引用，NULL表示根菜单）
    name       VARCHAR(100) NOT NULL,                                            -- 菜单名称（显示用）
    code       VARCHAR(100) UNIQUE,                                              -- 菜单编码（用于权限标识或 API 权限控制）
    path       VARCHAR(255),                                                     -- 前端路由路径或后端API路径
    component  VARCHAR(255),                                                     -- 前端组件路径（如 Layout、Dashboard）
    icon       VARCHAR(100),                                                     -- 菜单图标（可选）
    sort       SMALLINT                 DEFAULT 0,                               -- 排序字段
    menu_type  CHAR(1)      NOT NULL    DEFAULT 'M',                             -- 菜单类型：M-目录；C-菜单；A-按钮/操作
    status     SMALLINT                 DEFAULT 1,                               -- 菜单状态：1-启用；0-禁用
    visible    BOOLEAN                  DEFAULT TRUE,                            -- 是否在菜单栏中可见
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE ghost_menu IS '系统菜单表';

COMMENT ON COLUMN ghost_menu.id IS '菜单ID';
COMMENT ON COLUMN ghost_menu.parent_id IS '父菜单ID（自引用）';
COMMENT ON COLUMN ghost_menu.name IS '菜单名称';
COMMENT ON COLUMN ghost_menu.code IS '菜单编码（用于权限标识）';
COMMENT ON COLUMN ghost_menu.path IS '路由路径（前端或API路径）';
COMMENT ON COLUMN ghost_menu.component IS '前端组件路径（如 layouts/Dashboard.vue）';
COMMENT ON COLUMN ghost_menu.icon IS '菜单图标（如 fa-dashboard）';
COMMENT ON COLUMN ghost_menu.sort IS '排序字段（数字越小越靠前）';
COMMENT ON COLUMN ghost_menu.menu_type IS '菜单类型：M-目录；C-菜单；A-按钮/操作';
COMMENT ON COLUMN ghost_menu.status IS '菜单状态：1-启用；0-禁用';
COMMENT ON COLUMN ghost_menu.visible IS '是否在菜单栏中可见';
COMMENT ON COLUMN ghost_menu.created_at IS '创建时间';
COMMENT ON COLUMN ghost_menu.updated_at IS '最后更新时间';

-- 角色-菜单关联表：ghost_role_menu
-- 存储角色与菜单之间的多对多关系
CREATE TABLE ghost_role_menu
(
    role_id BIGINT NOT NULL REFERENCES ghost_role (id) ON DELETE CASCADE, -- 角色ID
    menu_id BIGINT NOT NULL REFERENCES ghost_menu (id) ON DELETE CASCADE, -- 菜单ID
    PRIMARY KEY (role_id, menu_id)                                        -- 联合主键
);

COMMENT ON TABLE ghost_role_menu IS '角色与菜单关联表';
COMMENT ON COLUMN ghost_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN ghost_role_menu.menu_id IS '菜单ID';

