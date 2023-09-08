package cn.ikun.carshop.service;

import cn.ikun.carshop.pojo.Admin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AdminService extends IService<Admin> {

    /**
     * 检查是否有当前管理员
     * @param admin
     * @return
     */
    Admin adminselect(Admin admin);


    /**
     * 新增一个新的管理员
     * @param admin
     * @return
     */
     int insertAdmin(Admin admin);


    /**
     * 根据分页情况返回所有的管理员
     * @param pageSize
     * @param pageNum
     * @return
     */
     Page<Admin> selectAll( int pageNum,int pageSize);

    /**
     * 禁用当前管理员
     * @param id
     * @return
     */
     int banAdminById(Long id);

    /**
     * 启用当前管理员
     * @param id
     * @return
     */
     int upAdminStById(Long id);


    /**
     * 重置某个管理员的密码
     * @param id
     * @return
     */
     int AdminPassWordReSet(Long id);

    /**
     * 管理员更改信息 这个不可以更改名字
     * @param admin
     * @return
     */
     int AdminMsgUpdate(Admin admin);



    /**
     * 根据ID指定查询某个管理员的信息
     * @param id
     * @return
     */
     Admin getAdminMsg(Long id);

    /**
     * 模糊查询管理员的信息
     * @param keyword
     * @return
     */
     List<Admin> getAmdin(String keyword);


    /**
     * 单独删除某个管理员
     * @param id
     * @return
     */
     int deleteAdminById(Long id);

    /**
     * 批量删除管理员
     * @param adminIds
     * @return
     */
     int deleteAdminByIds(List<Long> adminIds);


}
