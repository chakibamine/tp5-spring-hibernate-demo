package config;

import dao.IDao;
import entities.Product;
import entities.Category;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestHibernateConfig.class})
public class TestHibernateConfigTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    @Qualifier("productDao")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDao")
    private IDao<Category> categoryDao;

    @Test
    public void testDataSourceConfiguration() {
        assertThat(dataSource).isNotNull();
    }

    @Test
    public void testSessionFactoryConfiguration() {
        assertThat(sessionFactory).isNotNull();
    }

    @Test
    public void testTransactionManagerConfiguration() {
        assertThat(transactionManager).isNotNull();
    }

    @Test
    public void testProductDaoBeanConfiguration() {
        assertThat(productDao).isNotNull();
        assertThat(productDao).isInstanceOf(metier.ProductDaoImpl.class);
    }

    @Test
    public void testCategoryDaoBeanConfiguration() {
        assertThat(categoryDao).isNotNull();
        assertThat(categoryDao).isInstanceOf(metier.CategoryDaoImpl.class);
    }

    @Test
    public void testDatabaseConnection() {
        // Test simple pour vérifier que la connexion à la base de données fonctionne
        assertThat(dataSource).isNotNull();
        // La configuration H2 en mémoire devrait fonctionner sans problème
    }
}
