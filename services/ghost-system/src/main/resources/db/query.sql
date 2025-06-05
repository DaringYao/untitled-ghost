-- ##################################################
-- 🔍 用户与员工信息查询
-- ##################################################

-- 查询所有用户及其关联的员工信息（包括姓名、手机号、部门、岗位）
-- 用于展示系统中用户与员工的基本对应关系
SELECT
    u.id AS user_id,
    u.username,
    e.name AS employee_name,
    e.mobile,
    e.email,
    d.name AS department_name,
    p.name AS position_name
FROM ghost_user u
         LEFT JOIN ghost_employee e ON u.id = e.user_id
         LEFT JOIN ghost_department d ON e.department_id = d.id
         LEFT JOIN ghost_position p ON e.position_id = p.id;

-- ##################################################
-- 👥 用户与角色信息查询
-- ##################################################

-- 查询某个用户拥有的所有角色
-- 替换 'admin' 为你需要查询的用户名
SELECT
    r.id AS role_id,
    r.name AS role_name,
    r.code AS role_code
FROM ghost_user u
         JOIN ghost_user_role ur ON u.id = ur.user_id
         JOIN ghost_role r ON ur.role_id = r.id
WHERE u.username = 'admin';

-- ##################################################
-- 🧑‍💼 员工与岗位、部门信息查询
-- ##################################################

-- 查询某个员工所属的部门和岗位信息
-- 替换 e.id = 1 为你需要查询的员工ID
SELECT
    e.name AS employee_name,
    d.name AS department_name,
    p.name AS position_name
FROM ghost_employee e
         JOIN ghost_department d ON e.department_id = d.id
         JOIN ghost_position p ON e.position_id = p.id
WHERE e.id = 1;

-- ##################################################
-- 🏢 部门下的所有员工
-- ##################################################

-- 查询某部门下的所有员工信息
-- 替换 e.department_id = 1 为你需要查询的部门ID
SELECT
    e.id AS employee_id,
    e.name AS employee_name,
    e.mobile,
    p.name AS position_name
FROM ghost_employee e
         JOIN ghost_position p ON e.position_id = p.id
WHERE e.department_id = 1;

-- ##################################################
-- 🎯 岗位绑定的角色信息（可选功能）
-- ##################################################

-- 查询某个岗位所拥有的角色列表
-- 替换 p.id = 1 为你需要查询的岗位ID
SELECT
    p.name AS position_name,
    r.name AS role_name,
    r.code AS role_code
FROM ghost_position p
         JOIN ghost_position_role pr ON p.id = pr.position_id
         JOIN ghost_role r ON pr.role_id = r.id
WHERE p.id = 1;

-- ##################################################
-- 🔐 用户的所有角色 + 所属岗位角色（合并权限）
-- ##################################################

-- 查询用户的直接角色 + 所在岗位的角色（合并去重）
-- 替换 u.username = 'zhangsan' 为你需要查询的用户名
SELECT DISTINCT r.id, r.name, r.code
FROM ghost_user u
         LEFT JOIN ghost_user_role ur ON u.id = ur.user_id
         LEFT JOIN ghost_role r ON ur.role_id = r.id
         LEFT JOIN ghost_employee e ON u.id = e.user_id
         LEFT JOIN ghost_position_role pr ON e.position_id = pr.position_id
WHERE u.username = 'zhangsan'
  AND (r.id IS NOT NULL OR pr.role_id IS NOT NULL);

-- ##################################################
-- 📈 部门树形结构查询（如果使用了 ltree 扩展）
-- ##################################################

-- 安装 ltree 扩展（仅首次执行时需要）
-- CREATE EXTENSION IF NOT EXISTS ltree;

-- 查询某个部门及其所有子部门
-- 替换 path <@ '1.2' 中的路径为你实际使用的路径字段
SELECT id, name, code, path
FROM ghost_department
WHERE path <@ '1.2'; -- 示例路径，请根据实际数据替换

-- ##################################################
-- 📝 查询所有部门及下属员工数量（统计类查询）
-- ##################################################

-- 统计每个部门下的员工人数
SELECT
    d.id AS department_id,
    d.name AS department_name,
    COUNT(e.id) AS employee_count
FROM ghost_department d
         LEFT JOIN ghost_employee e ON d.id = e.department_id
GROUP BY d.id, d.name
ORDER BY employee_count DESC;