package cn.ikun.carshop.service;

import cn.ikun.carshop.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User userLogin(User user);

    /**
     * 用户增加
     * @param user
     * @return
     */
    int userAdd(User user);

    /**
     * 单独删除某个用户
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     * 多删
     * @param ids
     * @return
     */
    int deleteUserByListIds(List<Long> ids);

    /**
     * 改
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 查
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     * 分页获取信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<User> getUserPage(int pageNum,int pageSize);

    /**
     * 查找资料
     * @param msg
     * @return
     */
    List<User> getUserBySearch(String msg);

    /**
     * 禁止该用户登录
     * @param id
     * @return
     */
    int banStatusById(Long id);

    /**
     * 允许该用户登录
     * @param id
     * @return
     */
    int enableStatusById(Long id);

    /**
     * 获取性别分类
     * @return
     */
    List<Map<String,Object>> selectSexOrder();
}
