package cn.ikun.carshop.service;


import cn.ikun.carshop.utils.MessageUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;



public interface ImgService {

    /**
     * 租借车辆上传图片
     * @param multipartFile
     * @return
     */
    String saveImg(MultipartFile multipartFile);

    /**
     * 下载图片
     * @param name
     * @param response
     */
    void download(String name, HttpServletResponse response);

}
