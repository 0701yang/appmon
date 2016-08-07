import com.shsnc.entity.system.User;
import com.shsnc.service.system.UserService;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhangliling on 16/6/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*.xml"})
public class Test {

    @org.junit.Test
    public void testUser(){
        try{
            User u=new UserService().findById("1");
            System.out.println(u.getUsername());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
