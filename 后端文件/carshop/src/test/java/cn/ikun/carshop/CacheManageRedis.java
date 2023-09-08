package cn.ikun.carshop;


import cn.ikun.carshop.pojo.User;
import cn.ikun.carshop.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;

@SpringBootTest
public class CacheManageRedis {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test1(){
        redisTemplate.opsForValue().set("js","js应用框架");
        var js = redisTemplate.opsForValue().get("js");
        System.out.println(js);
    }

    @Test
    public void test2(){
        User user = new User();
        user.setName("周鲲");user.setUserName("zzk");
        redisTemplate.opsForValue().set("user",user);

        var u = redisTemplate.opsForValue().get("user");
        System.out.println(u);
    }

    @Test
    public void test3() throws JsonProcessingException {
        User user = new User();
        user.setName("诺克萨斯之手-德莱厄斯");user.setUserName("英雄之力");
        ObjectMapper mapper = new ObjectMapper();
        String usr =  mapper.writeValueAsString(user);

       stringRedisTemplate.opsForValue().set("user1",usr );
        var s = stringRedisTemplate.opsForValue().get("user1");
        var myusr = mapper.readValue(s, User.class);
        System.out.println(myusr);

    }


    @Test
    public void testUtils(){
        redisUtils.opsSetValue("ck","贵族货");
        var ck = redisUtils.opsGetValue("ck");
        System.out.println(ck);

    }

    @Test
    public void testHash(){

        User user1 = new User();
        user1.setName("诺克萨斯之手-德莱厄斯");user1.setUserName("诺手");

        User user2 = new User();
        user2.setName("德玛西亚之力-盖伦");user2.setUserName("德玛");

        redisTemplate.opsForHash().put("userPage","user1",user1);
        redisTemplate.opsForHash().put("userPage","user2",user2);
    }

    @Test
    public void getHash(){

        var page = redisUtils.opsGetAllHash("userPage");
        System.out.println(page);

        var obj = redisUtils.opsGetHashCol("userPage", "user2");
        System.out.println(obj);
    }

    @Test
    public void testgetHashNull()
    {
        var page = redisUtils.opsGetHashCol("userPage", "123");
        System.out.println(page);
        if (Optional.ofNullable(page).isPresent()){
            System.out.println(page);
        }else{
            System.out.println("没有查到该数据");
        }
    }

    @Test
    public void  testDelete(){

        //redisUtils.deleteHashByHashKey("userPage","user2");

        redisUtils.deleteAllHash("userPage");
    }



}
