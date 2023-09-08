package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.AdminMapper;
import cn.ikun.carshop.pojo.Admin;
import cn.ikun.carshop.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Value("${carshop.salt}")
    private String salt;


    /**
     * 检查是否有当前管理员
     *
     * @param admin
     * @return
     */
    @Override
    public Admin adminselect(Admin admin) {
        String hashpassword = DigestUtils.md5DigestAsHex((admin.getPassword() + salt).getBytes(StandardCharsets.UTF_8));
        admin.setPassword(hashpassword);
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, admin.getUsername())
                .eq(Admin::getPassword, admin.getPassword());
        Admin ad = adminMapper.selectOne(queryWrapper);
        if (Optional.ofNullable(ad).isPresent()) {
            return ad;
        } else {
            return null;
        }
    }

    /**
     * 新增一个新的管理员
     *
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public int insertAdmin(Admin admin) {
        String hashpassword = DigestUtils.md5DigestAsHex(("123456" + salt).getBytes(StandardCharsets.UTF_8));
        admin.setPassword(hashpassword);
        var res = adminMapper.insert(admin);
        return res;
    }


    @Override
    public Page<Admin> selectAll( int pageNum,int pageSize) {

        Page<Admin> page = new Page<>(pageNum,pageSize);

        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.select("admin_id","username","name","sex",
                "tel", "id_card","email","role",
                "status","create_time","update_time","update_admins");
        adminMapper.selectPage(page, wrapper);
        return page;
    }

    @Transactional
    /**
     * 禁用当前管理员
     *
     * @param id
     * @return
     */
    @Override
    public int banAdminById(Long id) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Admin::getStatus, 1)
                .set(Admin::getUpdateTime,LocalDateTime.now())
                .eq(Admin::getAdminId, id);
        return adminMapper.update(new Admin(), wrapper);

    }

    /**
     * 启用当前管理员
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int upAdminStById(Long id) {
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Admin::getStatus, 0)
                .set(Admin::getUpdateTime,LocalDateTime.now())
                .eq(Admin::getAdminId, id);
        return adminMapper.update(new Admin(), wrapper);
    }

    /**
     * 重置某个管理员的密码
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int AdminPassWordReSet(Long id) {
        String hashpassword = DigestUtils.md5DigestAsHex(("123456" + salt).getBytes(StandardCharsets.UTF_8));
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Admin::getPassword, hashpassword)
                .set(Admin::getUpdateTime,LocalDateTime.now())
                .eq(Admin::getAdminId, id);

        return adminMapper.update(new Admin(), wrapper);
    }

    /**
     * 管理员更改信息 这个不可以更改名字
     * @param admin
     * @return
     */
    @Transactional
    @Override
    public int AdminMsgUpdate(Admin admin) {

        String hashpassword = DigestUtils.md5DigestAsHex((admin.getPassword()+salt).getBytes(StandardCharsets.UTF_8));
        LambdaUpdateWrapper<Admin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Admin::getPassword,hashpassword)
                .set(Admin::getName,admin.getName())
                .set(Admin::getSex,admin.getSex())
                .set(Admin::getTel,admin.getTel())
                .set(Admin::getIdCard,admin.getIdCard())
                .set(Admin::getEmail,admin.getEmail())
                .eq(Admin::getAdminId,admin.getAdminId());
        return adminMapper.update(new Admin(),wrapper);
    }


    @Override
    public Admin getAdminMsg(Long id) {
        LambdaQueryWrapper<Admin> wrapper =  new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAdminId,id);
        return adminMapper.selectOne(wrapper);
    }

    /**
     * 模糊查询管理员的信息
     * @param keyword
     * @return
     */
    @Override
    public List<Admin> getAmdin(String keyword) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Admin::getAdminId,keyword).or()
                .like(Admin::getUsername,keyword).or()
                .like(Admin::getName,keyword).or()
                .like(Admin::getTel,keyword).or()
                .like(Admin::getIdCard,keyword).or()
                .like(Admin::getEmail,keyword);
        return adminMapper.selectList(wrapper);
    }

    /**
     * 单独删除某个管理员
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int deleteAdminById(Long id) {
        return adminMapper.deleteById(id);
    }


    /**
     * 批量删除管理员
     * @param adminIds
     * @return
     */
    @Override
    @Transactional
    public int deleteAdminByIds(List<Long> adminIds) {

        return adminMapper.deleteBatchIds(adminIds);
    }
}
