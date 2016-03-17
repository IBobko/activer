import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ru.todo100.activer.util.MailBean;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class MailTest {
    @Test
    public void t(){


        ApplicationContext app = new FileSystemXmlApplicationContext("file:C:/Users/User/IdeaProjects2/activer/src/main/webapp/WEB-INF/spring/test-context.xml");
        MailBean mailService = (MailBean)app.getBean("mailBean");
        mailService.sendCompleteSignUp(null);

    }
}
