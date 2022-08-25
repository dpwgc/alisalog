package com.dpwgc.alisalog.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件操作
 */
public class FileUtil {

    /**
     * 文件写入
     * @param content  待写入内容
     */
    public static void write(String content) throws IOException {
        Files.write(Paths.get(System.getProperty("user.dir")+"/alisa-log-token.json"), content.getBytes());
    }

    /**
     * 文件读取
     */
    public static String read() throws IOException {
        return Files.readAllLines(Paths.get(System.getProperty("user.dir")+"/alisa-log-token.json")).get(0);
    }
}
