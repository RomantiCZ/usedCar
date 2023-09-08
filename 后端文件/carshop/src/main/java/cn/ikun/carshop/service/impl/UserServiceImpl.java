package cn.ikun.carshop.service.impl;

import cn.ikun.carshop.mapper.UserMapper;
import cn.ikun.carshop.pojo.User;
import cn.ikun.carshop.service.UserService;
import cn.ikun.carshop.utils.JwtConfigUtils;
import cn.ikun.carshop.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Value("${carshop.salt}")
    private String salt;

    @Autowired
    private JwtConfigUtils jwtConfigUtils;

    @Autowired
    private RedisUtils redisUtils;

    private String key = "userPage";

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) {
        String hashPassword = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes(StandardCharsets.UTF_8));

        var myUser = userMapper.selectByUserNameAndPassword(user.getUserName());
        if (Optional.ofNullable(myUser).isPresent() && myUser.getPassword().equals(hashPassword)) {
            myUser.setUserToken(jwtConfigUtils.createUserToken("user" + myUser.getUserId(), myUser));
            myUser.setPassword(null);
            return myUser;

        } else {
            return null;
        }

    }

    /**
     * 用户增加
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public int userAdd(User user) {
        String hashPassword = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes(StandardCharsets.UTF_8));
        user.setPassword(hashPassword);

        //删除缓存
        redisUtils.deleteAllHash(this.key);

        return userMapper.insert(user);
    }

    /**
     * 删除某个用户
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int deleteUserById(Long id) {
        //删除缓存
        redisUtils.deleteAllHash(this.key);
        return userMapper.deleteById(id);
    }


    /**
     * 多删
     *
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public int deleteUserByListIds(List<Long> ids) {
        //删除缓存
        redisUtils.deleteAllHash(this.key);

        return userMapper.deleteBatchIds(ids);
    }

    /**
     * 改
     *
     * @param user
     * @return
     */
    @Transactional
    @Override
    public int updateUserById(User user) {
        String hashPassword = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes(StandardCharsets.UTF_8));
        user.setPassword(hashPassword);
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();

        wrapper.set(User::getUserName, user.getUserName()).set(User::getPassword, user.getPassword()).set(User::getName, user.getName()).set(User::getSex, user.getSex()).set(User::getIdCard, user.getIdCard()).set(User::getTel, user.getTel()).set(User::getEmail, user.getEmail()).set(User::getImg, user.getImg()).set(User::getUpdateTime, LocalDateTime.now())

                .eq(User::getUserId, user.getUserId());

        //删除缓存
        redisUtils.deleteAllHash(this.key);

        return userMapper.update(user, wrapper);
    }

    /**
     * 查
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {

        return userMapper.selectById(id);
    }

    /**
     * 查找信息
     *
     * @param msg
     * @return
     */
    @Override
    public List<User> getUserBySearch(String msg) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.like(User::getName, msg);
        return userMapper.selectList(wrapper);
    }

    /**
     * 禁止该用户登录
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int banStatusById(Long id) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getStatus, 1).set(User::getUpdateTime, LocalDateTime.now()).eq(User::getUserId, id);

        //删除缓存
        redisUtils.deleteAllHash(this.key);
        return userMapper.update(new User(), wrapper);
    }

    /**
     * 允许该用户登录
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int enableStatusById(Long id) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(User::getStatus, 0).set(User::getUpdateTime, LocalDateTime.now()).eq(User::getUserId, id);

        //删除缓存
        redisUtils.deleteAllHash(this.key);

        return userMapper.update(new User(), wrapper);
    }

    /**
     * 分页获取信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getUserPage(int pageNum, int pageSize) {


        String hashKey = "userMan" + "_pageNum_" + pageNum + "_pageSize_" + pageSize;
        var userPage = redisUtils.opsGetHashCol(key, hashKey);

        if (Optional.ofNullable(userPage).isPresent()) {
            return (Page<User>) userPage;
        } else {
            Page<User> page = new Page<>(pageNum, pageSize);
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.select("user_id", "username", "name", "sex", "img", "email", "tel", "status");
            userMapper.selectPage(page, wrapper);
            redisUtils.opsSetHash(key, hashKey, page);
            return page;

        }


    }

    /**
     * 获取性别分类
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> selectSexOrder() {
        return userMapper.selectSexOrder();
    }
}
