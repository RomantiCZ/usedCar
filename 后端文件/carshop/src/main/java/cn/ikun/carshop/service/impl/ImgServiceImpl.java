package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.service.ImgService;
import cn.ikun.carshop.utils.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Service
@Slf4j
public class ImgServiceImpl implements ImgService {

    @Value("${carshop.photopath}")
    private String basePhotoPath;

    @Override
    public String saveImg(MultipartFile multipartFile) {

        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        File file = new File(basePhotoPath);
        if (!file.exists()) {
            file.mkdir();
        }
        //使用UUID重新生成文件名，防止文件名称重复造成文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;//dfsdfdfd.jpg
        try {
            multipartFile.transferTo(new File(basePhotoPath+fileName));
            return fileName;
        } catch (Exception e) {
            log.info("发生Io错误：{}", e);
            e.printStackTrace();
        }

       return null;
    }

    @Override
    public void download(String name, HttpServletResponse response) {
            try {
                //输入流，通过输入流读取文件内容
                FileInputStream fileInputStream = new FileInputStream(new File(basePhotoPath+"/" + name));
                //输出流，通过输出流将文件写回浏览器,在浏览器展示图片
                ServletOutputStream servletOutputStream = response.getOutputStream();
                response.setContentType("image/jpeg");

                int len = 0;
                byte[] bytes = new byte[2048];
                while ((len = fileInputStream.read(bytes)) != -1) {
                    servletOutputStream.write(bytes, 0, len);
                    servletOutputStream.flush();
                }
                //关闭资源
                servletOutputStream.close();
                fileInputStream.close();

            }catch (Exception e){
                log.info("服务器发生错误");
                e.printStackTrace();
            }
    }
}
