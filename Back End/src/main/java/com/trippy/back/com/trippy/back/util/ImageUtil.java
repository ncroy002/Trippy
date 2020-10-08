package com.trippy.back.com.trippy.back.util;

import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

public class ImageUtil {

    private ImageUtil(){
        throw new IllegalStateException("This is a util class");
    }

    public static Blob convert(MultipartFile file) throws Exception{
//        Scanner sc = new Scanner(new BufferedReader(new
//                FileReader("myData")));
//        Blob blob = null;
//        while(sc.hasNext()) {
//            byte[] myArray = sc.nextLine().getBytes();
//            blob = new SerialBlob(myArray );
//        }
        return new SerialBlob(file.getBytes());
    }
}
