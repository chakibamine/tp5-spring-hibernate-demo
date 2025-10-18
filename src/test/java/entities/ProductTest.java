package entities;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    @Test
    public void testProductCreation() {
        // Given
        Category category = new Category();
        category.setId(1);
        category.setCode("ELECTRONICS");

        // When
        Product product = new Product();
        product.setId(1);
        product.setName("Laptop");
        product.setPrice(999.99);
        product.setCategory(category);

        // Then
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("Laptop");
        assertThat(product.getPrice()).isEqualTo(999.99);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    public void testProductConstructor() {
        // Given
        Category category = new Category();
        category.setCode("ELECTRONICS");

        // When
        Product product = new Product("Smartphone", 599.99, category);

        // Then
        assertThat(product.getName()).isEqualTo("Smartphone");
        assertThat(product.getPrice()).isEqualTo(599.99);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    public void testProductSettersAndGetters() {
        // Given
        Product product = new Product();
        Category category = new Category();

        // When
        product.setId(123);
        product.setName("Tablet");
        product.setPrice(299.99);
        product.setCategory(category);

        // Then
        assertThat(product.getId()).isEqualTo(123);
        assertThat(product.getName()).isEqualTo("Tablet");
        assertThat(product.getPrice()).isEqualTo(299.99);
        assertThat(product.getCategory()).isEqualTo(category);
    }

    @Test
    public void testProductWithoutCategory() {
        // Given
        Product product = new Product();

        // When
        product.setName("Generic Product");
        product.setPrice(49.99);
        // Pas de catégorie assignée

        // Then
        assertThat(product.getName()).isEqualTo("Generic Product");
        assertThat(product.getPrice()).isEqualTo(49.99);
        assertThat(product.getCategory()).isNull();
    }
}
