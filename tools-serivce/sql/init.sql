use mysql;
-- 创建用户
create user nwg IDENTIFIED by 'npU#UTXpJWQ3aLkEctrip123';
-- 分配权限 （注意大小写敏感）
grant select, insert, update, delete,create on YUEBANHU_TOOLS.* to nwg;

flush privileges;


CREATE SCHEMA `YUEBANHU_TOOLS`;


