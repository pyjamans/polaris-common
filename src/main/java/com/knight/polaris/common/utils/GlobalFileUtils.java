package com.knight.polaris.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

public class GlobalFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(GlobalFileUtils.class);

    /**
     * 文件读取工具类，将文件内容读取输出为字符串
     * 实现逻辑借用Files工具类的实现
     * @param path 路径
     * @param charset 字符集编码
     * @return
     */
    public static String readAllLinesToString(Path path, Charset charset) {
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(provider(path).newInputStream(path), charset.newDecoder()))) {
            String line;
            while ((line = reader.readLine()) != null){
                builder.append(line);
            }
            return builder.toString();
        }catch (IOException e) {
            logger.error("Read file fail![文件读取异常!]", e);
            return null;
        }
    }

    /**
     * 文件读取工具类，将文件内容读取输出为字符串
     * 实现逻辑借用Files工具类的实现
     * 使用默认UTF-8的字符集处理
     * @param path 路径
     * @return
     */
    public static String readAllLinesToString(Path path) {
        return readAllLinesToString(path, StandardCharsets.UTF_8);
    }

    public static String readAllLinesToString(String inputFile) {
        File file = new File(inputFile);
        if(file.exists()) {
            return readAllLinesToString(file.toPath(), StandardCharsets.UTF_8);
        }
        return null;
    }

    /**
     * 获取文件系统provider
     * @param path 路径
     * @return
     */
    private static FileSystemProvider provider(Path path) {
        return path.getFileSystem().provider();
    }
}
