/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import bll.helpers.FileExtensions;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.FileEvent;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class FileHandler implements FileBase {

    public FileHandler() {
    }

    @Override
    public void ClientSender(ObjectOutputStream oos, File file, String destinationPath) {

        // tạo đối tượng FileEvent
        FileEvent fileEvent = new FileEvent();

        fileEvent.setFilename(FileExtensions.getFileName(file));
        fileEvent.setDestinationDirectory(destinationPath);

        if (file.isFile()) {
            try {
                // DataInputStream đọc dữ liệu nguyên thủy từ luồng đầu vào ( ở đây là file đầu vào)
                DataInputStream diStream = new DataInputStream(new FileInputStream(file));
                long len = (int) file.length();
                byte[] fileBytes = new byte[(int) len];
                int read = 0;
                int numRead = 0;
                while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read, fileBytes.length - read)) >= 0) {
                    read = read + numRead;
                }

                fileEvent.setFileSize(len);
                fileEvent.setFileData(fileBytes);
                fileEvent.setStatus("Success");
            } catch (Exception e) {
                e.printStackTrace();
                fileEvent.setStatus("Error");
            }
        } else {
            System.out.println("Không tìm thấy tệp ở vị trí đã chỉ định.");
            fileEvent.setStatus("Error");
        }

        //Ghi đối tượng tệp vào thư mục
        try {
            oos.writeObject(fileEvent);
            System.out.println("Đang gửi file...");
            Thread.sleep(3000);
            System.out.println("Hoàn tất!");
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ClientSaver(ObjectInputStream ois) {
        try {
            // Bước 1: đọc đối tượng truyền từ client qua
            FileEvent fileEvent = (FileEvent) ois.readObject();
            
            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi khi truyền file..");
            }
            
            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            
            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " được ghi thành công. ");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void ServerSaver(ObjectInputStream ois) {
        try {
            // Bước 1: đọc đối tượng truyền từ client qua
            FileEvent fileEvent = (FileEvent) ois.readObject();
            
            // Bước 2: Kiểm tra trạng thái có bị "Error" ko???
            if (fileEvent.getStatus().equalsIgnoreCase("Error")) {

                // Nếu có: Thông báo lỗi => Chấm dứt chương trình
                System.err.println("Xảy ra lỗi khi truyền file..");
            }
            
            // tạo file
            String outputFile = fileEvent.getDestinationDirectory() + fileEvent.getFilename();
            
            // FileOutputStream dùng để ghi dữ liệu vào file theo định dạng byte
            FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
            fileOutputStream.write(fileEvent.getFileData());    // bđ ghi vô luồng
            fileOutputStream.flush();   // đẩy luồng ghi vô file
            fileOutputStream.close();   // đóng quá trình ghi sau khi hoàn tất

            System.out.println("Output file : " + outputFile + " được ghi thành công. ");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
