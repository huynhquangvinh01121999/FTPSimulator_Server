-- ID_01: thêm cột PermissionId vào table Users
alter table Users
add PermissionId nchar(255) default 'all'

-- ID_02(01/12/1999):
-- .thêm cột AnonymousPermission vào table Users
alter table Users
add AnonymousPermission nchar(255) default 'unlock'

-- .thêm cột FolderUserPermission vào table Folders
alter table Folders
add FolderUserPermission nchar(255) default 'unlock'