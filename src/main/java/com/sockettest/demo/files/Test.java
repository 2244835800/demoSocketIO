import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author:hepo
 * @Version:v1.0
 * @Description:
 * @Date:2019/2/20/020
 * @Time:15:47
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/application.properties");
        context.start();
        context.getBean("chatServer");
    }
}
