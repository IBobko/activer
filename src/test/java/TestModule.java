import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import ru.todo100.activer.model.GiftItem;

import java.net.MalformedURLException;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class TestModule {
   // @Test
    public void test(){
        System.out.println("hello");


//
//        MetadataSources metadata = new MetadataSources(
//                new StandardServiceRegistryBuilder()
//                        .applySetting("hibernate.dialect", org.hibernate.dialect.Oracle8iDialect.class)
//                        .build());

        //metadata.addAnnotatedClass(AuthorityItem.class);
        //metadata.addAnnotatedClass(AccountItem.class);
        //metadata.addAnnotatedClass(AccountFriendRelationItem.class);
        //metadata.addAnnotatedClass(MessageItem.class);
        //metadata.addAnnotatedClass(AccountPhotoItem.class);
        //metadata.addAnnotatedClass(WallItem.class);







//
//
//        SchemaExport gen = new SchemaExport((MetadataImplementor)metadata.buildMetadata());
//        //gen.setOutputFile("C://Users/User/1.sql");
//        gen.setDelimiter(";");
//        gen.execute(true, false, false, false);

        System.out.println("test");
    }

    @Test
    public void test2() throws MalformedURLException {
//        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:/WEB-INF/spring/root-context.xml");
//
//        AccountItem accountItem = new AccountItem();
//        accountItem.setEmail("limit-speed@yandex.ru");
//        accountItem.setFirstName("Igor");
//        accountItem.setLastName("Bobko");
//        accountItem.setUsername("limit-speed@yandex.ru");
//        accountItem.setPassword("tGTQosyZ19");
//
//        MailBean mailBean = (MailBean)app.getBean("mailService");
//        //System.out.println(new URL("file://classpath:/").getPath());
//        mailBean.sendCompleteSignUp(accountItem);

//        System.out.println("hello");
//
//
//
//s
    }
}
