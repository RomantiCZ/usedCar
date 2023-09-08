package cn.ikun.carshop.controller;

import cn.ikun.carshop.pojo.Admin;
import cn.ikun.carshop.service.AdminService;
import cn.ikun.carshop.utils.JwtConfigUtils;
import cn.ikun.carshop.utils.MessageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtConfigUtils jwtConfigUtils;

    //登录管理员
    @PostMapping("/login")
    public MessageUtil<Admin> login(@RequestBody Admin admin, HttpServletRequest request) throws Exception {

        var session = request.getSession();
        var ad = adminService.adminselect(admin);
        if (Optional.ofNullable(ad).isPresent()) {

            if (ad.getStatus() == 0) {
                Admin jwtAdmin = new Admin();
                String[] properties = {"password", "sex", "tel", "idCard", "email", "status", "createTime", "updateTime", "updateAdmins", "isDelete"};
                BeanUtils.copyProperties(ad, jwtAdmin, properties);
                var token = jwtConfigUtils.createAdminToken("admin" + jwtAdmin.getAdminId(), jwtAdmin);
                log.info("当前admin获取的token信息:->{}", token);
                jwtAdmin.setAdminToken(token);
                return MessageUtil.successForMsg(jwtAdmin, "登录成功");
            } else {
                return MessageUtil.error("当前账号被禁用");
            }
        } else {
            return MessageUtil.error("账号或密码错误");
        }

    }

    @PostMapping("/tokenCheck")
    public MessageUtil<Admin> tokenCheck(@RequestParam("token") String token) {
        var admin = jwtConfigUtils.parseAdminToken(token);
        if (Optional.ofNullable(admin).isPresent()) {
            return MessageUtil.successForMsg(admin, "解析成功");
        } else {
            return MessageUtil.error("管理员信息失效,请重新登录");
        }
    }

    //新增管理员
    @PostMapping("/insert")
    public MessageUtil<String> adminInsert(@RequestBody Admin admin) {
        System.out.println(admin);
        var res = adminService.insertAdmin(admin);
        if (res > 0) {
            return MessageUtil.success("管理员添加成功");
        } else {
            return MessageUtil.error("管理员添加失败");
        }
    }

    //分页获取管理员信息
    @GetMapping("/page")
    public MessageUtil<Page<Admin>> getAdmin(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize) {
        log.info("pageNum:{}  ->  pageSize:{}", pageNum, pageSize);
        var page = adminService.selectAll(pageNum, pageSize);
        if (Optional.ofNullable(page).isPresent()) {
            return MessageUtil.successForMsg(page, "查询成功");
        } else {
            return MessageUtil.error("查询失败");
        }

    }

    //指定查询某个管理员信息
    @GetMapping("/getAdminMsg/{id}")
    public MessageUtil<Admin> getAdminMsg(@PathVariable Long id) {

        var admin = adminService.getAdminMsg(id);
        if (Optional.ofNullable(admin).isPresent()) {
            return MessageUtil.successForMsg(admin, "查询成功");
        }
        return MessageUtil.error("查询失败");
    }

    //根据id模糊查询某个管理员信息
    @GetMapping("/getMsg")
    public MessageUtil<List<Admin>> getMsgAdminList(@RequestParam("keyword") String keyword) {
        var adminList = adminService.getAmdin(keyword);
        if (adminList.size() > 0) {
            return MessageUtil.successForMsg(adminList, "查询成功");
        }
        return MessageUtil.error("查询失败,没有相应信息");
    }


    //普通管理员更改信息
    @PostMapping("/updateAdmin")
    public MessageUtil<String> adminMsgUp(@RequestBody Admin admin) {

        var res = adminService.AdminMsgUpdate(admin);
        if (res > 0) {
            return MessageUtil.success("信息更改成功");
        }
        return MessageUtil.error("信息更改失败");
    }



    //超级管理员重置普通管理员的密码
    @GetMapping("/reset/{id}")
    public MessageUtil<String> reset(@PathVariable Long id) {
        var res = adminService.AdminPassWordReSet(id);
        if (res > 0) {
            return MessageUtil.success("密码重置成功");
        }
        return MessageUtil.error("密码重置失败");
    }

    //禁用某个管理员
    @GetMapping("/ban/{id}")
    public MessageUtil<String> banAdmin(@PathVariable Long id) {
        var res = adminService.banAdminById(id);
        if (res > 0) {
            return MessageUtil.success("禁用当前管理员");
        } else {
            return MessageUtil.error("禁用当前管理员失败");
        }
    }

    //启用某个管理员
    @GetMapping("/enable/{id}")
    public MessageUtil<String> admin(@PathVariable Long id) {
        var res = adminService.upAdminStById(id);
        if (res > 0) {
            return MessageUtil.success("启用当前管理员");
        } else {
            return MessageUtil.error("启用当前管理员失败");
        }
    }



    //单独删除某个管理员
    @GetMapping("/delete/{id}")
    public MessageUtil<String> delete(@PathVariable Long id) {
        var res = adminService.deleteAdminById(id);
        if (res > 0) {
            return MessageUtil.success("删除成功");
        } else {
            return MessageUtil.error("删除失败");
        }


    }

    //批量删除管理员的信息
    @PostMapping("/deleteByIds")
    public MessageUtil<String> deleteByIds(@RequestBody List<Long> adminIds) {
//        log.info("Ids:{}", Arrays.toString(adminIds.toArray()));
        var res = adminService.deleteAdminByIds(adminIds);
        if (res > 0) {
            return MessageUtil.success("删除成功");
        } else {
            return MessageUtil.error("删除失败");
        }
    }

}
