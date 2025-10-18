package controllers;

import dao.IDao;
import entities.Product;
import entities.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private IDao<Product> productDao;

    @Mock
    private IDao<Category> categoryDao;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;
    private Product testProduct;
    private Category testCategory;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        testCategory = new Category();
        testCategory.setId(1);
        testCategory.setCode("ELECTRONICS");

        testProduct = new Product();
        testProduct.setId(1);
        testProduct.setName("Laptop");
        testProduct.setPrice(999.99);
        testProduct.setCategory(testCategory);
    }

    @Test
    public void testListProducts_ReturnsCorrectView() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productDao.findAll()).thenReturn(products);

        // When
        String viewName = productController.listProducts(model);

        // Then
        assertThat(viewName).isEqualTo("products/list");
        verify(model).addAttribute("products", products);
    }

    @Test
    public void testShowCreateForm_ReturnsCorrectView() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(categoryDao.findAll()).thenReturn(categories);

        // When
        String viewName = productController.showCreateForm(model);

        // Then
        assertThat(viewName).isEqualTo("products/form");
        verify(model).addAttribute(eq("product"), any(Product.class));
        verify(model).addAttribute("categories", categories);
    }

    @Test
    public void testSaveProduct_RedirectsToList() {
        // Given
        when(productDao.create(any(Product.class))).thenReturn(true);

        // When
        String viewName = productController.saveProduct(testProduct);

        // Then
        assertThat(viewName).isEqualTo("redirect:/products");
        verify(productDao).create(testProduct);
    }

    @Test
    public void testShowEditForm_ReturnsCorrectView() {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(productDao.findById(1)).thenReturn(testProduct);
        when(categoryDao.findAll()).thenReturn(categories);

        // When
        String viewName = productController.showEditForm(1, model);

        // Then
        assertThat(viewName).isEqualTo("products/form");
        verify(model).addAttribute("product", testProduct);
        verify(model).addAttribute("categories", categories);
    }

    @Test
    public void testUpdateProduct_RedirectsToList() {
        // Given
        when(productDao.update(any(Product.class))).thenReturn(true);

        // When
        String viewName = productController.updateProduct(testProduct);

        // Then
        assertThat(viewName).isEqualTo("redirect:/products");
        verify(productDao).update(testProduct);
    }

    @Test
    public void testDeleteProduct_RedirectsToList() {
        // Given
        when(productDao.findById(1)).thenReturn(testProduct);
        when(productDao.delete(any(Product.class))).thenReturn(true);

        // When
        String viewName = productController.deleteProduct(1);

        // Then
        assertThat(viewName).isEqualTo("redirect:/products");
        verify(productDao).findById(1);
        verify(productDao).delete(testProduct);
    }

    @Test
    public void testDeleteProduct_NonExistingProduct() {
        // Given
        when(productDao.findById(999)).thenReturn(null);

        // When
        String viewName = productController.deleteProduct(999);

        // Then
        assertThat(viewName).isEqualTo("redirect:/products");
        verify(productDao).findById(999);
        verify(productDao, never()).delete(any(Product.class));
    }

    @Test
    public void testListProducts_MvcRequest() throws Exception {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productDao.findAll()).thenReturn(products);

        // When & Then
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/list"));
    }

    @Test
    public void testShowCreateForm_MvcRequest() throws Exception {
        // Given
        List<Category> categories = Arrays.asList(testCategory);
        when(categoryDao.findAll()).thenReturn(categories);

        // When & Then
        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("products/form"));
    }
}
