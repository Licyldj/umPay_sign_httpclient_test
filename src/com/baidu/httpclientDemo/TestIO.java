package com.baidu.httpclientDemo;

import java.io.File;
import java.io.FileOutputStream;

public class TestIO {

    public static void main(String[] args) throws Exception {
        FileOutputStream out = null;
        int count = 1000;// 写文件行数
        out = new FileOutputStream(new File("D:\\workspaces\\eclipse_x64\\umPay_sign_httpclient_test\\src\\com\\baidu\\mp3\\add.txt"));
        for (int i = 0; i < count; i++) {

            out.write("测试java 文件操作\r\n".getBytes());

        }
        out.close();
    }

}
