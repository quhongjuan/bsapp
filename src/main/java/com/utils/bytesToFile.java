package com.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class bytesToFile {
    /**
     * 文件byte[]类型转File
     *
     * @param bytes     bytes
     * @return
     */

    public static File bytesToFile(byte[] bytes) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
//            File dir = new File(outPath);
//            if (!dir.exists() && dir.isDirectory()) { //判断文件目录是否存在
//                dir.mkdirs();
//            }
//            file = new File(outPath + File.separator + fileName);
            file=new File("hecheng.pdf");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }


}
