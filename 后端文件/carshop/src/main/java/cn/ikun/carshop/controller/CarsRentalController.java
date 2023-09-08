package cn.ikun.carshop.controller;


import cn.ikun.carshop.pojo.CarsRental;
import cn.ikun.carshop.service.CarsRentalService;
import cn.ikun.carshop.service.CarsSaleService;
import cn.ikun.carshop.service.ImgService;
import cn.ikun.carshop.utils.MessageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/carRental")
public class CarsRentalController {
    @Autowired
    private CarsRentalService carsRentalService;

    @Autowired
    private ImgService imgService;

    @PostMapping("/addCar")
    public MessageUtil<String> addCars(@RequestBody CarsRental carsRental) {
        var res = carsRentalService.addCarsRental(carsRental);
        if (res > 0) {
            return MessageUtil.success("车辆添加成功");
        } else {
            return MessageUtil.error("车辆添加失败");
        }
    }

    //单删
    @GetMapping("/delete/{id}")
    public MessageUtil<String> deleteCarsRentalById(@PathVariable("id") String id) {

        var res = carsRentalService.deleteCarsRentalById(id);
        if (res > 0) {
            return MessageUtil.success("车辆删除成功");

        } else {
            return MessageUtil.error("删除失败");
        }
    }

    //多删
    @PostMapping("/deleteByIds")
    public MessageUtil<String> deleteByIds(@RequestBody List<Long> ids) {
        var res = carsRentalService.deleteCarByIds(ids);
        if (res > 0) {
            return MessageUtil.success("批量删除成功");
        }
        return MessageUtil.error("删除失败");
    }

    @PostMapping("/updateCarRental")
    public MessageUtil<String> updateCarRental(@RequestBody CarsRental carsRental) {
        var res = carsRentalService.UpdateCarsRentalById(carsRental);
        if (res > 0) {
            return MessageUtil.success("更新成功");
        }
        return MessageUtil.error("更新失败");
    }

    /**
     * 管理员页获取
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public MessageUtil<Page<CarsRental>> getPageCarsRental(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {

        var page = carsRentalService.getCarsRentalPage(pageNum, pageSize);
        if (page.getTotal() >= 0) {
            return MessageUtil.success(page);
        }
        return MessageUtil.error("获取失败");
    }

    /**
     * 用户分页获取
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/userpage")
    public MessageUtil<Page<CarsRental>> userGetPageCarsRental(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        var page = carsRentalService.userGetCarRentalPage(pageNum,pageSize);
        if (page.getTotal() >= 0) {
            return MessageUtil.success(page);
        }
        return MessageUtil.error("获取失败");
    }

    @GetMapping("/getCarRental/{id}")
    public MessageUtil<CarsRental> getCarRental(@PathVariable("id") String id) {
        var carsRental = carsRentalService.getCarsRentalById(id);
        if (Optional.ofNullable(carsRental).isPresent()) {
            return MessageUtil.success(carsRental);
        } else {
            return MessageUtil.error("查询失败,没有车辆信息");
        }
    }


    @GetMapping("/ban/{id}")
    public MessageUtil<String> banCarsRental(@PathVariable("id") String id) {
        var res = carsRentalService.banCarsRentalById(id);
        if (res > 0) {
            return MessageUtil.success("该车辆下架成功");

        } else {
            return MessageUtil.error("该车辆下架失败");
        }
    }

    @GetMapping("/enable/{id}")
    public MessageUtil<String> enableCarsRental(@PathVariable("id") String id) {
        var res = carsRentalService.enableCarsRentalById(id);
        if (res > 0) {
            return MessageUtil.success("该车辆启用成功");

        } else {
            return MessageUtil.error("该车辆启用失败");
        }

    }

    @GetMapping("/getBrand")
    public MessageUtil<List<Map<String, Object>>> getBrand() {

        return MessageUtil.success(carsRentalService.getBrandCount());
    }

    @GetMapping("/getYear")
    public MessageUtil<List<Map<String, Object>>> getYear() {
        return MessageUtil.success(carsRentalService.getYearCount());
    }

    @GetMapping("/getNew")
    public MessageUtil<List<CarsRental>> getNewCarRental() {
        return MessageUtil.success(carsRentalService.getUpToDateRentalCar());
    }

    @GetMapping("/search")
    public MessageUtil<List<CarsRental>> getSerach(@RequestParam("keyword") String msg) {
        var carsRental = carsRentalService.sarchCarsRentalBySearch(msg);
        if (carsRental.size() > 0) {
            return MessageUtil.successForMsg(carsRental, "查找成功");
        } else {
            return MessageUtil.error("查找失败，无此信息");
        }
    }

    /**
     * 租借车辆上传图片
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/addImg")
    public MessageUtil<String> addImgSave(@RequestParam("multipartPhoto") MultipartFile multipartFile) {
        var photoUrl = imgService.saveImg(multipartFile);
        if (Optional.ofNullable(photoUrl).isPresent()) {
            return MessageUtil.successForMsg(photoUrl, "图片上传成功");
        } else {
            return MessageUtil.error("图片上传失败");
        }

    }

    /**
     * 租借车辆下载图片
     *
     * @param name
     * @param response
     */
    @GetMapping("/downloadImg")
    public void downloadImg(@RequestParam("name") String name, HttpServletResponse response) {
        imgService.download(name, response);
    }


}
