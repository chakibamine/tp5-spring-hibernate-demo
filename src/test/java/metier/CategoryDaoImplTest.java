package metier;

import dao.IDao;
import entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryDaoImplTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private CategoryDaoImpl categoryDao;

    private Category testCategory;

    @Before
    public void setUp() {
        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setCode("ELECTRONICS");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testCreateCategory_Success() {
        // Given
        when(session.save(any(Category.class))).thenReturn(1);

        // When
        boolean result = categoryDao.create(testCategory);

        // Then
        assertThat(result).isTrue();
        verify(session).save(testCategory);
    }

    @Test
    public void testDeleteCategory_Success() {
        // Given
        doNothing().when(session).delete(any(Category.class));

        // When
        boolean result = categoryDao.delete(testCategory);

        // Then
        assertThat(result).isTrue();
        verify(session).delete(testCategory);
    }

    @Test
    public void testUpdateCategory_Success() {
        // Given
        doNothing().when(session).update(any(Category.class));

        // When
        boolean result = categoryDao.update(testCategory);

        // Then
        assertThat(result).isTrue();
        verify(session).update(testCategory);
    }

    @Test
    public void testFindById_ExistingCategory() {
        // Given
        when(session.get(Category.class, 1)).thenReturn(testCategory);

        // When
        Category result = categoryDao.findById(1);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getCode()).isEqualTo("ELECTRONICS");
        verify(session).get(Category.class, 1);
    }

    @Test
    public void testFindById_NonExistingCategory() {
        // Given
        when(session.get(Category.class, 999)).thenReturn(null);

        // When
        Category result = categoryDao.findById(999);

        // Then
        assertThat(result).isNull();
        verify(session).get(Category.class, 999);
    }

    @Test
    public void testFindAll_WithCategories() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(session.createQuery(anyString(), eq(Category.class))).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery(anyString(), eq(Category.class)).list()).thenReturn(categories);

        // When
        List<Category> result = categoryDao.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCode()).isEqualTo("ELECTRONICS");
    }

    @Test
    public void testFindAll_EmptyList() {
        // Given
        when(session.createQuery(anyString(), eq(Category.class))).thenReturn(mock(org.hibernate.query.Query.class));
        when(session.createQuery(anyString(), eq(Category.class)).list()).thenReturn(Arrays.asList());

        // When
        List<Category> result = categoryDao.findAll();

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
