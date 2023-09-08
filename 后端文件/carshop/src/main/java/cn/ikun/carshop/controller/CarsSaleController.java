package cn.ikun.carshop.controller;

import cn.ikun.carshop.pojo.CarsSale;
import cn.ikun.carshop.service.CarsSaleService;
import cn.ikun.carshop.service.ImgService;
import cn.ikun.carshop.utils.MessageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carsSale")
public class CarsSaleController {

    @Autowired
    private CarsSaleService carsSaleService;

    @Autowired
    private ImgService imgService;

    @PostMapping("/add")
    public MessageUtil<String> carSaleAdd(@RequestBody CarsSale carsSale){
        var res = carsSaleService.carSaleAdd(carsSale);
        if (res>0){
            return MessageUtil.success("添加成功");
        }else{
            return MessageUtil.error("添加失败");
        }

    }

    /**
     * 单删
     * @param id
     * @return
     */
    @GetMapping("/deleteByid/{id}")
    public MessageUtil<String> deleteById(@PathVariable("id")Long id){
        var res = carsSaleService.carSaleDeleteById(id);

        if (res>0){
            return MessageUtil.success("车辆删除成功");
        }else{
            return  MessageUtil.error("车辆删除失败");
        }

    }

    /**
     * 多删
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public MessageUtil<String> deleteByIds(@RequestBody List<Long> ids){
        var res = carsSaleService.carSaleDeleteByIds(ids);
        if (res>0){
            return MessageUtil.success("批量删除成功");
        }else {
            return MessageUtil.error("批量删除失败");
        }

    }


    /**
     * 改
     * @param carsSale
     * @return
     */
    @PostMapping("/update")
    public MessageUtil<String> updateByid(@RequestBody CarsSale carsSale){
        var res = carsSaleService.updateCarSaleById(carsSale);
        if (res>0){
            return MessageUtil.success("车辆信息修改成功");
        }else {
            return MessageUtil.error("车辆信息修改失败");
        }

    }

    /**
     * 查
     * @param id
     * @return
     */
    @GetMapping("/getMsg/{id}")
    public MessageUtil<CarsSale> getMsgById(@PathVariable("id") Long id){
        var carsSale = carsSaleService.getCarSaleById(id);
        if (Optional.ofNullable(carsSale).isPresent()){
            return MessageUtil.success(carsSale);
        }
        return MessageUtil.error("查找失败");
    }

    @GetMapping("/search")
    public MessageUtil<List<CarsSale>> searchByMsg(@RequestParam("keyword")String keyword){
        var carsSaleList = carsSaleService.getMsgBySearch(keyword);
        if (carsSaleList.size()>0){
            return MessageUtil.successForMsg(carsSaleList,"查询成功");
        }else {
            return  MessageUtil.error("没有该车辆信息");
        }
    }


    /**
     * 分页获取信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public MessageUtil<Page<CarsSale>> getPage(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        var page = carsSaleService.getCarSalePageList(pageNum, pageSize);
        if (page.getTotal()>0){
            return MessageUtil.success(page);
        }else {
            return MessageUtil.error("获取失败");
        }

    }

    /**
     * 用户获取
     * @param
     * @return
     */
    @GetMapping("/userpage")
    public MessageUtil<Page<CarsSale>> getUserPage(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        var page = carsSaleService.userGetCarSalePageList(pageNum,pageSize);
        if (page.getTotal()>0){
            return MessageUtil.success(page);
        }else{
            return MessageUtil.error("获取失败");
        }

    }


    @GetMapping("/ban/{id}")
    public MessageUtil<String> banStatus(@PathVariable("id")Long id){
        var res = carsSaleService.banStatus(id);
        if (res>0){
            return MessageUtil.success("操作成功");
        }else{
            return MessageUtil.error("下架失败");
        }
    }

    @GetMapping("/enable/{id}")
    public MessageUtil<String> enableStatus(@PathVariable("id")Long id){
        var res = carsSaleService.enableStatus(id);
        if (res>0){
            return MessageUtil.success("操作成功");
        }else{
            return MessageUtil.error("启用失败");
        }

    }

    @GetMapping("/getBrand")
    public MessageUtil<List<Map<String,Object>>> getBrand(){
        var list = carsSaleService.selectAllBrand();
        return MessageUtil.success(list);
    }

    @GetMapping("/getYear")
    public MessageUtil<List<Map<String,Object>>> getYear(){
        var list = carsSaleService.selectAllYear();
        return MessageUtil.success(list);
    }

    @GetMapping("/getNew")
    public MessageUtil<List<CarsSale>> getNew(){
        return MessageUtil.success(carsSaleService.getUpToDateRentalCar());
    }


    /**
     * 租借车辆上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/addImg")
    public MessageUtil<String> addImgSave(@RequestParam("multipartPhoto") MultipartFile multipartFile){
        var photoUrl = imgService.saveImg(multipartFile);
        if (Optional.ofNullable(photoUrl).isPresent()){
            return MessageUtil.successForMsg(photoUrl,"图片上传成功");
        }else {
            return MessageUtil.error("图片上传失败");
        }

    }

    /**
     * 租借车辆下载图片
     * @param name
     * @param response
     */
    @GetMapping("/downloadImg")
    public void downloadImg(@RequestParam("name") String name , HttpServletResponse response){
        imgService.download(name, response);
    }



}
