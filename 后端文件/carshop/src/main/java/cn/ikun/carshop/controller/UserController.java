package cn.ikun.carshop.controller;



import cn.ikun.carshop.pojo.User;
import cn.ikun.carshop.service.ImgService;
import cn.ikun.carshop.service.UserService;
import cn.ikun.carshop.utils.MessageUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j


public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImgService imgService;


    @PostMapping("/login")
    public MessageUtil<User> UserLogin(@RequestBody User user) {
        var myUser = userService.userLogin(user);
        if (Optional.ofNullable(myUser).isPresent()) {
            if (myUser.getStatus()==0){
                return MessageUtil.successForMsg(myUser, "登录成功");
            }else{
                return MessageUtil.error("该用户禁止登录");
            }

        } else {
            return MessageUtil.error("账号或密码错误");
        }

    }

    @PostMapping("/add")
    public MessageUtil<String> Useradd(@RequestBody User user) {
        var res = userService.userAdd(user);
        if (res > 0) {
            return MessageUtil.success("用户注册成功");
        } else {
            return MessageUtil.error("用户注册失败");
        }
    }

    @GetMapping("/getUserInfo/{id}")
    public MessageUtil<User> getUserInfoById(@PathVariable("id")Long id){
        var user = userService.getUserById(id);
        if (Optional.ofNullable(user).isPresent()){
            return MessageUtil.success(user);
        }else{
            return MessageUtil.error("查询失败");
        }
    }



    @PostMapping("/update")
    public MessageUtil<String> updateUserInfo(@RequestBody User user){
        var res = userService.updateUserById(user);
        if (res>0){
            return MessageUtil.success("用户信息更新成功");
        }else{
            return MessageUtil.error("用户信息更新失败");
        }
    }

    @GetMapping("/getSexOrder")
    public MessageUtil< List<Map<String, Object>>> getSexOrder(){
        var list = userService.selectSexOrder();
        if (list.size()>0){
            return MessageUtil.success(list);
        }else{
            return MessageUtil.error("查询失败");
        }
    }

    @GetMapping("/page")
    public MessageUtil<Page<User>> getUserPage(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize){
        var page = userService.getUserPage(pageNum, pageSize);
        if (page.getTotal()>0){
            return MessageUtil.success(page);
        }else
        {
            return MessageUtil.error("查询失败");
        }

    }

    @GetMapping("/search")
    public MessageUtil<List<User>> getUserBySearch(@RequestParam("keyword") String msg){
        var userList = userService.getUserBySearch(msg);
        if (userList.size()>0){
            return MessageUtil.successForMsg(userList,"查询成功");
        }else{
            return MessageUtil.error("查询失败,没有该用户信息");
        }
    }


    @GetMapping("/enable/{id}")
    public MessageUtil<String> enableUser(@PathVariable("id")Long id){
        var res = userService.enableStatusById(id);
        if (res>0){
            return MessageUtil.success("启用成功,允许该用户登录");
        }else{
            return MessageUtil.error("启用失败,不允许该用户登录");
        }
    }

    @GetMapping("/ban/{id}")
    public MessageUtil<String> banUser(@PathVariable("id")Long id){

        var res = userService.banStatusById(id);
        if (res>0){
            return MessageUtil.success("禁用成功,不允许该用户登录");
        }else{
            return MessageUtil.error("禁用失败");
        }
    }

    /**
     * 图片上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/addImg")
    public MessageUtil<String> ImgUpload(@RequestParam("multipartPhoto") MultipartFile multipartFile){
        var img = imgService.saveImg(multipartFile);
        if (Optional.ofNullable(img).isPresent()){
            return MessageUtil.successForMsg(img,"图片上传成功");
        }else{
            return MessageUtil.error("图片上传失败");
        }
    }

    @GetMapping("/downloadImg")
    public void downLoadImg(@RequestParam("name") String name , HttpServletResponse response){
        imgService.download(name,response);
    }
}
