import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsh.JpaAndRedisApplication;
import com.xsh.dao.AccountDao;
import com.xsh.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaAndRedisApplication.class)
public class JpaAndRedisApplicationTests {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void JpaTest() {
        List<Account> all = accountDao.findAll();
        System.out.println(all);
    }

    @Test
    public void test() throws Exception {
        //1、从redis中获得数据 数据的形式json字符串
        String accountListJson = redisTemplate.boundValueOps("account.findAll").get();
        //2、判断redis中是否存在数据
        if(null==accountListJson){
            //3、不存在数据 从数据库查询
            List<Account> all = accountDao.findAll();
            //4、将查询出的数据存储到redis缓存中
            //向将list集合转换成json格式的字符串  使用jackson进行转换
            ObjectMapper objectMapper = new ObjectMapper();
            accountListJson = objectMapper.writeValueAsString(all);
            redisTemplate.boundValueOps("account.findAll").set(accountListJson);

            System.out.println("=======从数据库中获得account的数据======");
        }else{
            System.out.println("=======从redis缓存中获得account的数据======");
        }

        //4、将数据在控制台打印
        System.out.println(accountListJson);

    }
}
