import org.hibernate.tool.schema.TargetType;
import org.junit.Assert;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.todo100.activer.dao.AccountDao;
import ru.todo100.activer.model.AccountItem;
import ru.todo100.activer.model.AuthorityItem;
import ru.todo100.activer.model.PromoCodeItem;
import ru.todo100.activer.service.PromoService;
import ru.todo100.activer.service.ReferService;
import ru.todo100.activer.util.MailService;

import java.util.EnumSet;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class MailTest {

    ApplicationContext applicationContext;


    @Before
    public void before() {
        //applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
    }

    @Test
    public void test(){
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:/spring/root-context.xml");
        MailService mailService = (MailService)app.getBean("mailService");
        AccountItem accountItem = new AccountItem();
        accountItem.setEmail("limit-speed@yandex.ru");
        accountItem.setLastName("Igor");
        accountItem.setFirstName("Bobko");
        accountItem.setUsername("limit-speed@yandex.ru");
        accountItem.setPassword("tGTQosyZ19");
        mailService.sendCompleteSignUp(accountItem);

    }

    private AccountItem getTestAccount() {
        AccountItem accountItem = new AccountItem();
        accountItem.setEmail("limit-speed@yandex.ru");
        accountItem.setLastName("Igor");
        accountItem.setFirstName("Bobko");
        accountItem.setUsername("limit-speed@yandex.ru");
        accountItem.setPassword("tGTQosyZ19");
        return accountItem;
    }


    //@Test
    public void generatePromo() {
        PromoService promoService = (PromoService)applicationContext.getBean("promocodeService");
        AccountItem accountItem = getTestAccount2();
        System.out.println(promoService.generateNewPromo(accountItem));
    }

    //@Test
    public void showAccountsPromo() {
        PromoService promoService = (PromoService)applicationContext.getBean("promocodeService");
        AccountItem accountItem = getTestAccount2();

        for (PromoCodeItem promoCodeItem : promoService.getAccountPromos(accountItem))
        {
            System.out.println(promoCodeItem);
        }

    }


    //@Test
    public void checkPromo() {
        PromoService promoService = (PromoService)applicationContext.getBean("promocodeService");
        PromoCodeItem promo = promoService.getPromo("7565");
        if (promo != null && promo.getUsed() == null) {
            System.out.println("Промо найден и он свободен");
        }

        PromoCodeItem promo2 = promoService.getPromo("7566");
        if (promo2 == null) {
            System.out.println("Промо не найден");
        }


//        AccountItem accountItem = getTestAccount2();
//
//        for (PromoCodeItem promoCodeItem : promoService.getAccountPromos(accountItem))
//        {
//            System.out.println(promoCodeItem);
//        }

    }

    //@Test
    public void referTest() {
        ReferService referService = (ReferService)applicationContext.getBean("referService");
        AccountItem account = referService.getUserByRefer("jKitrS");
        if (account != null) {
            System.out.println(account  + " Пользователь с такой реферальной ссылкой найден");
        } else {
            System.out.println("Пользователь с такой реферальной ссылкой не найден");
        }
    }

    private AccountItem getTestAccount2() {
        AccountDao accountService = (AccountDao)applicationContext.getBean("accountService");
        AccountItem accountItem = accountService.get("limit-speed@yandex.ru");
        Assert.assertNotNull(accountItem);
        return accountItem;
    }


    //@Test
    public void modelGenerator() {


        MetadataSources metadata = new MetadataSources(
                new StandardServiceRegistryBuilder()
                        .applySetting("hibernate.dialect", org.hibernate.dialect.Oracle8iDialect.class)
                        .build());

        metadata.addAnnotatedClass(PromoCodeItem.class);
        metadata.addAnnotatedClass(AccountItem.class);
        metadata.addAnnotatedClass(AuthorityItem.class);

        EnumSet<TargetType> targetTypes = EnumSet.of(TargetType.STDOUT);

        SchemaExport gen = new SchemaExport();
        gen.setOutputFile("C://Users/User/1.sql");
        gen.setDelimiter(";");
        gen.setFormat(true);
        gen.createOnly(targetTypes,metadata.buildMetadata());
    }
}
