package integration;

import dao.IDao;
import entities.Product;
import entities.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {config.TestHibernateConfig.class})
@Transactional
public class ProductDaoIntegrationTest {

    @Autowired
    @Qualifier("productDao")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDao")
    private IDao<Category> categoryDao;

    private Category testCategory;
    private Product testProduct;

    @Before
    public void setUp() {
        // Créer une catégorie de test
        testCategory = new Category();
        testCategory.setCode("TEST_CATEGORY");
        categoryDao.create(testCategory);

        // Créer un produit de test
        testProduct = new Product();
        testProduct.setName("Test Product");
        testProduct.setPrice(99.99);
        testProduct.setCategory(testCategory);
    }

    @Test
    public void testCreateAndFindProduct() {
        // When
        boolean created = productDao.create(testProduct);
        Product found = productDao.findById(testProduct.getId());

        // Then
        assertThat(created).isTrue();
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Test Product");
        assertThat(found.getPrice()).isEqualTo(99.99);
        assertThat(found.getCategory()).isNotNull();
        assertThat(found.getCategory().getCode()).isEqualTo("TEST_CATEGORY");
    }

    @Test
    public void testUpdateProduct() {
        // Given
        productDao.create(testProduct);
        Product original = productDao.findById(testProduct.getId());

        // When
        original.setName("Updated Product");
        original.setPrice(149.99);
        boolean updated = productDao.update(original);
        Product found = productDao.findById(original.getId());

        // Then
        assertThat(updated).isTrue();
        assertThat(found.getName()).isEqualTo("Updated Product");
        assertThat(found.getPrice()).isEqualTo(149.99);
    }

    @Test
    public void testDeleteProduct() {
        // Given
        productDao.create(testProduct);
        int productId = testProduct.getId();
        Product created = productDao.findById(productId);
        assertThat(created).isNotNull();

        // When
        boolean deleted = productDao.delete(created);
        Product found = productDao.findById(productId);

        // Then
        assertThat(deleted).isTrue();
        assertThat(found).isNull();
    }

    @Test
    public void testFindAllProducts() {
        // Given
        productDao.create(testProduct);

        Product anotherProduct = new Product();
        anotherProduct.setName("Another Product");
        anotherProduct.setPrice(199.99);
        anotherProduct.setCategory(testCategory);
        productDao.create(anotherProduct);

        // When
        List<Product> products = productDao.findAll();

        // Then
        assertThat(products).isNotNull();
        assertThat(products).hasSize(2);
        assertThat(products).extracting(Product::getName)
                .containsExactlyInAnyOrder("Test Product", "Another Product");
    }

    @Test
    public void testProductCategoryRelationship() {
        // Given
        Category electronics = new Category();
        electronics.setCode("ELECTRONICS");
        categoryDao.create(electronics);

        Category clothing = new Category();
        clothing.setCode("CLOTHING");
        categoryDao.create(clothing);

        Product laptop = new Product();
        laptop.setName("Laptop");
        laptop.setPrice(999.99);
        laptop.setCategory(electronics);

        Product shirt = new Product();
        shirt.setName("T-Shirt");
        shirt.setPrice(29.99);
        shirt.setCategory(clothing);

        // When
        productDao.create(laptop);
        productDao.create(shirt);

        List<Product> products = productDao.findAll();

        // Then
        assertThat(products).hasSize(2);
        
        Product foundLaptop = products.stream()
                .filter(p -> p.getName().equals("Laptop"))
                .findFirst()
                .orElse(null);
        
        Product foundShirt = products.stream()
                .filter(p -> p.getName().equals("T-Shirt"))
                .findFirst()
                .orElse(null);

        assertThat(foundLaptop).isNotNull();
        assertThat(foundLaptop.getCategory().getCode()).isEqualTo("ELECTRONICS");
        
        assertThat(foundShirt).isNotNull();
        assertThat(foundShirt.getCategory().getCode()).isEqualTo("CLOTHING");
    }
}
