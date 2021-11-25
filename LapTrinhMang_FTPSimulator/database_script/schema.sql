
create database LapTrinhMang_FTPSimulator

------------------------------------------------------------------------

USE [LapTrinhMang_FTPSimulator]
GO
/****** Object:  Table [dbo].[Files]    Script Date: 26/11/2021 1:19:54 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Files](
	[FileId] [nchar](255) NOT NULL,
	[FileName] [nvarchar](255) NOT NULL,
	[SourcePath] [nvarchar](255) NULL,
	[FileSize] [nvarchar](255) NOT NULL,
	[FileExtension] [nvarchar](255) NOT NULL,
	[Status] [nvarchar](255) NOT NULL,
	[FolderId] [nchar](255) NOT NULL,
	[UploadAt] [nvarchar](255) NULL,
	[PrexEmail] [nchar](100) NULL,
 CONSTRAINT [PK_Files] PRIMARY KEY CLUSTERED 
(
	[FileId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FileShares]    Script Date: 26/11/2021 1:19:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FileShares](
	[FileId] [nchar](200) NOT NULL,
	[FromEmail] [nchar](100) NOT NULL,
	[ToEmail] [nchar](100) NOT NULL,
	[PermissionId] [nvarchar](255) NOT NULL,
	[ShareAt] [nvarchar](255) NULL,
 CONSTRAINT [PK_FileShares] PRIMARY KEY CLUSTERED 
(
	[FileId] ASC,
	[FromEmail] ASC,
	[ToEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Folders]    Script Date: 26/11/2021 1:19:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Folders](
	[FolderId] [nchar](255) NOT NULL,
	[FolderName] [nvarchar](255) NOT NULL,
	[FolderPath] [nvarchar](255) NOT NULL,
	[Email] [nchar](255) NOT NULL,
	[Size] [nvarchar](255) NOT NULL,
	[RemainingSize] [nvarchar](255) NULL,
	[CreateAt] [nchar](255) NULL,
	[FolderParentId] [nchar](255) NULL,
 CONSTRAINT [PK_Folders] PRIMARY KEY CLUSTERED 
(
	[FolderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FolderShares]    Script Date: 26/11/2021 1:19:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FolderShares](
	[FolderId] [nchar](200) NOT NULL,
	[FromEmail] [nchar](100) NOT NULL,
	[ToEmail] [nchar](100) NOT NULL,
	[PermissionId] [nvarchar](255) NOT NULL,
	[ShareAt] [nvarchar](255) NULL,
 CONSTRAINT [PK_FolderShares] PRIMARY KEY CLUSTERED 
(
	[FolderId] ASC,
	[FromEmail] ASC,
	[ToEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Permissions]    Script Date: 26/11/2021 1:19:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Permissions](
	[PermissionId] [nchar](255) NOT NULL,
	[PermissionName] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Permissions] PRIMARY KEY CLUSTERED 
(
	[PermissionId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 26/11/2021 1:19:55 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Email] [nchar](255) NOT NULL,
	[Password] [nchar](255) NOT NULL,
	[FullName] [nvarchar](255) NOT NULL,
	[Sex] [nvarchar](10) NULL,
	[Dob] [nvarchar](255) NULL,
	[Status] [nvarchar](255) NOT NULL,
	[CreateAt] [nchar](255) NULL,
	[FileSizeUpload] [nchar](255) NULL,
	[FileSizeDownload] [nchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ('102400') FOR [FileSizeUpload]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ('102400') FOR [FileSizeDownload]
GO
