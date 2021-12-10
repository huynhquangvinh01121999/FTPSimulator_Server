/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FileExtensions {

    // chọn file: void
    public static void FileChooser() {
        JFileChooser imageFileChooser = new JFileChooser(System.getProperty("user.home"));

        // filter show file with extension
//        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image files", "png", "jpg");
//        imageFileChooser.setFileFilter(fileNameExtensionFilter);
        imageFileChooser.setDialogTitle("Chọn file");

        int imageChooser = imageFileChooser.showOpenDialog(null);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            try {
                File file = imageFileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                System.out.println(file);
                System.out.println("File name: " + file.getName());
                System.out.println("Path: " + path);
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi khi chọn file " + e);
            }
        }
    }

    // lấy ra đường dẫn file được chọn
    public static String getSourceFilePathChooser() {
        JFileChooser imageFileChooser = new JFileChooser(System.getProperty("user.home"));

        FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image files", "png", "jpg");

        imageFileChooser.setFileFilter(fileNameExtensionFilter);
        imageFileChooser.setDialogTitle("Chọn file");

        int imageChooser = imageFileChooser.showOpenDialog(null);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            try {
                File file = imageFileChooser.getSelectedFile();
                return replaceBackslashes(file.getAbsolutePath());
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi khi chọn file " + e);
            }
        }
        return null;
    }

    // Chọn file -> return File
    public static File getFileChooser() {
        JFileChooser imageFileChooser = new JFileChooser(System.getProperty("user.home"));

        imageFileChooser.setDialogTitle("Chọn file");

        int imageChooser = imageFileChooser.showOpenDialog(null);

        if (imageChooser == JFileChooser.APPROVE_OPTION) {
            try {
                File file = imageFileChooser.getSelectedFile();
                return file;
            } catch (Exception e) {
                System.err.println("Xảy ra lỗi khi chọn file " + e);
            }
        }
        return null;
    }

    // thay thế kí tự \ thành /
    public static String replaceBackslashes(String path) {
        return path.trim().replaceAll("[^A-Za-z0-9\\s\\s+:(.)_-]", "/");
    }

    // lấy ra đường dẫn tuyệt đối của thư mục source code đang làm việc
    public static String getLocalWorkFullPath() {
        return replaceBackslashes(Paths.get(".").toAbsolutePath().normalize().toString());
    }

    // lấy ra đường dẫn tương đối của thư mục source code đang làm việc
    public static String getLocalWorkSortPath() {
        return replaceBackslashes(Paths.get(".").toAbsolutePath().normalize().toString().split(":")[1]);
    }

    // tạo mới thư mục chưa tồn tại theo tên chỉ định
    public static boolean generateFolder(String desPath) {
        if (!new File(desPath).exists()) {
            return new File(desPath).mkdirs();
        }
        return false;
    }

    // lấy kích thước file: bytes
    private static long getFileSize(String sourceFilePath) {
        try {
            Path path = Paths.get(sourceFilePath);
            long bytes = Files.size(path);
            return bytes;
        } catch (IOException ex) {
            System.err.println("Lỗi khi đọc kích thước file" + ex);
        }
        return 0;
    }

    // chuyển đổi kích thước file: byte(B), kilobyte(KB) 
    public static String convertSize(String sourceFilePath, String key) {
        long fileSize = getFileSize(sourceFilePath);
        switch (key.toUpperCase()) {
            case "B":
                return String.format("%,d", fileSize);
            case "KB":
                String s = String.format("%,d.%,d", fileSize / 1024, fileSize % 1024);
                s = s.contains(".") ? s.replaceAll("0", "").replaceAll("\\.$", "") : s;
                return s;
        }
        return null;
    }

    // lấy ra tên file từ file chỉ định
    public static String getFileName(File file) {
//        String sourceFilePath = replaceBackslashes(file.getAbsolutePath());
//        return sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
        return file.getName();
    }

    // build
    public static void main(String[] args) {
//        System.out.println(FileExtension.getLocalWorkSortPath());
//        File file = FileExtensions.getFileChooser();
        System.out.println(FileExtensions.getLocalWorkSortPath());
    }
}
