import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import ru.todo100.activer.model.*;

/**
 * @author Igor Bobko <ibobko@beeline.ru>.
 */
public class TestModule {
    @Test
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
        //metadata.addAnnotatedClass(ICanItem.class);
        //metadata.addAnnotatedClass(IWantItem.class);
        //metadata.addAnnotatedClass(MessageItem.class);
        //metadata.addAnnotatedClass(PhotoItem.class);
        //metadata.addAnnotatedClass(WallItem.class);







//
//
//        SchemaExport gen = new SchemaExport((MetadataImplementor)metadata.buildMetadata());
//        //gen.setOutputFile("C://Users/User/1.sql");
//        gen.setDelimiter(";");
//        gen.execute(true, false, false, false);

        System.out.println("test");
    }
}
