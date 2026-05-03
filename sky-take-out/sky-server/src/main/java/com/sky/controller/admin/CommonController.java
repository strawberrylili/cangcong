package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.LocalUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
//    /**
//     * 文件上传
//     * @param file
//     * @return
//     */
//    @PostMapping("/upload")
//    @ApiOperation("文件上传")
//    public Result<String> upload(MultipartFile file)
//    {
//        log.info("文件上传：{}", file);
//        return null;
//    }
    /**
     * 本地磁盘上传目录
     */
    @Value("${sky.upload.path}")
    private String uploadPath;

    /**
     * 图片访问 URL 前缀
     */
    @Value("${sky.upload.url-prefix}")
    private String urlPrefix;

    /**
     * 文件上传
     *
     * @param file 前端上传的图片文件
     * @return 图片访问地址
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            String url = LocalUploadUtil.upload(file, uploadPath, urlPrefix);
            return Result.success(url);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("文件上传失败");
        }
    }
}
