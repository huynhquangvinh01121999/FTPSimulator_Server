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

-- ID_03(04/12/1999):
-- setup file size download/upload max 1MB
alter table Users drop constraint DF__Users__FileSizeD__5BE2A6F2
alter table Users drop constraint DF__Users__FileSizeU__5AEE82B9

ALTER TABLE Users ADD CONSTRAINT DF__Users__FileSizeD__5BE2A6F2  DEFAULT '1048576' FOR FileSizeDownload
ALTER TABLE Users ADD CONSTRAINT DF__Users__FileSizeU__5AEE82B9  DEFAULT '1048576' FOR FileSizeUpload