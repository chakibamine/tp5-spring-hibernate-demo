package metier;

import dao.IDao;
import entities.Product;
import entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDaoImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private ProductDaoImpl productDao;

    private Product testProduct;
    private Category testCategory;

    @Before
    public void setUp() {
        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setCode("ELECTRONICS");

        testProduct = new Product();
        testProduct.setId(1);
        testProduct.setName("Laptop");
        testProduct.setPrice(999.99);
        testProduct.setCategory(testCategory);

        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testCreateProduct_Success() {
        // Given
        when(session.save(any(Product.class))).thenReturn(1);

        // When
        boolean result = productDao.create(testProduct);

        // Then
        assertThat(result).isTrue();
        verify(session).save(testProduct);
    }

    @Test
    public void testDeleteProduct_Success() {
        // Given
        doNothing().when(session).delete(any(Product.class));

        // When
        boolean result = productDao.delete(testProduct);

        // Then
        assertThat(result).isTrue();
        verify(session).delete(testProduct);
    }

    @Test
    public void testUpdateProduct_Success() {
        // Given
        doNothing().when(session).update(any(Product.class));

        // When
        boolean result = productDao.update(testProduct);

        // Then
        assertThat(result).isTrue();
        verify(session).update(testProduct);
    }

    @Test
    public void testFindById_ExistingProduct() {
        // Given
        when(session.get(Product.class, 1)).thenReturn(testProduct);

        // When
        Product result = productDao.findById(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Laptop");
        assertThat(result.getPrice()).isEqualTo(999.99);
        verify(session).get(Product.class, 1);
    }

    @Test
    public void testFindById_NonExistingProduct() {
        // Given
        when(session.get(Product.class, 999)).thenReturn(null);

        // When
        Product result = productDao.findById(999);

        // Then
        assertThat(result).isNull();
        verify(session).get(Product.class, 999);
    }

    @Test
    public void testFindAll_WithProducts() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        org.hibernate.query.Query<Product> mockQuery = mock(org.hibernate.query.Query.class);
        when(session.createQuery(anyString(), eq(Product.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(products);

        // When
        List<Product> result = productDao.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Laptop");
    }

    @Test
    public void testFindAll_EmptyList() {
        // Given
        org.hibernate.query.Query<Product> mockQuery = mock(org.hibernate.query.Query.class);
        when(session.createQuery(anyString(), eq(Product.class))).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(Arrays.asList());

        // When
        List<Product> result = productDao.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
