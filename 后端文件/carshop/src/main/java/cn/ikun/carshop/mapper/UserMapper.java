package cn.ikun.carshop.mapper;

import cn.ikun.carshop.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUserNameAndPassword(@Param("username") String userName);

    List<Map<String, Object>> selectSexOrder();
}
