package com.admin.luntan.web;

import com.admin.luntan.dto.UeditorImgUploadResultDto;
import com.admin.luntan.util.FileUtil;
import com.admin.luntan.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * 文件上传公用
 * Created by zhanghaichao on 2018/2/3.
 */
@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    private static final String IMGUPLOADPATH= "E:/APP/JAVA/GIT/file/img";//图片保存路径

    private static final String IMGREQUESTURL = "staticfile";//网页请求图片中间url

    /**
     * 文件上传具体实现方法（单文件上传）
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("upfile") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String[] date = TimeUtil.getYearMonthDay();
                String fileOldName = file.getOriginalFilename();
                String uploadFileSuffix = fileOldName.substring(
                        fileOldName.indexOf('.') + 1, fileOldName.length());
                String fileid = UUID.randomUUID().toString();
                String path = String.format("/%s/%s%s/%s.%s",date[0], date[1], date[2], fileid, uploadFileSuffix);
                String filePath = String.format("%s%s", IMGUPLOADPATH, path);
                File targetFile = new File(filePath);

                if (!targetFile.getParentFile().exists()) { //判断文件父目录是否存在
                    FileUtil.createFile(filePath);
                }

                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(targetFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                String returnPath = String.format("%s/img%s",IMGREQUESTURL,path);

                UeditorImgUploadResultDto resultDto = new UeditorImgUploadResultDto();
                resultDto.setName(fileOldName);
                resultDto.setState("SUCCESS");
                resultDto.setUrl(returnPath);
                return resultDto.toJson();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

}
