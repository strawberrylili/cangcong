package com.sky.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 本地文件上传工具类
 */
public class LocalUploadUtil {

    /**
     * 将上传的文件保存到本地磁盘，并返回浏览器可访问的图片 URL
     *
     * @param file       前端上传的文件
     * @param uploadPath 本地磁盘保存路径，例如 D:/cangqiong/upload/
     * @param urlPrefix  浏览器访问前缀，例如 http://localhost:8080/images/
     * @return 图片访问地址
     */
    public static String upload(MultipartFile file, String uploadPath, String urlPrefix) throws IOException {

        // 1. 获取原始文件名，例如 abc.jpg
        String originalFilename = file.getOriginalFilename();

        // 2. 获取文件后缀，例如 .jpg / .png
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 3. 使用 UUID 生成新文件名，避免文件名重复
        String fileName = UUID.randomUUID().toString() + suffix;

        // 4. 判断本地上传目录是否存在，不存在就创建
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 5. 创建目标文件对象
        File dest = new File(uploadPath + fileName);

        // 6. 把上传的文件保存到目标位置
        file.transferTo(dest);

        // 7. 返回浏览器可以访问的图片路径
        return urlPrefix + fileName;
    }
}
