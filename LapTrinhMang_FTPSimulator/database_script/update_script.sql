-- ID_01
alter table Users
add PermissionId nchar(255) default 'all'

-- ID_02(01/12/1999) : thêm cột AnonymousPermission
alter table Users
add AnonymousPermission nchar(255) default 'unlock'
