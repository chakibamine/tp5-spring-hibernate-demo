package entities;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @Test
    public void testCategoryCreation() {
        // When
        Category category = new Category();
        category.setId(1);
        category.setCode("ELECTRONICS");

        // Then
        assertThat(category.getId()).isEqualTo(1);
        assertThat(category.getCode()).isEqualTo("ELECTRONICS");
    }

    @Test
    public void testCategorySettersAndGetters() {
        // Given
        Category category = new Category();

        // When
        category.setId(456);
        category.setCode("CLOTHING");

        // Then
        assertThat(category.getId()).isEqualTo(456);
        assertThat(category.getCode()).isEqualTo("CLOTHING");
    }

    @Test
    public void testCategoryWithDifferentCodes() {
        // Given
        Category electronics = new Category();
        Category clothing = new Category();
        Category books = new Category();

        // When
        electronics.setCode("ELECTRONICS");
        clothing.setCode("CLOTHING");
        books.setCode("BOOKS");

        // Then
        assertThat(electronics.getCode()).isEqualTo("ELECTRONICS");
        assertThat(clothing.getCode()).isEqualTo("CLOTHING");
        assertThat(books.getCode()).isEqualTo("BOOKS");
    }

    @Test
    public void testCategoryCodeUpdate() {
        // Given
        Category category = new Category();
        category.setCode("OLD_CODE");

        // When
        category.setCode("NEW_CODE");

        // Then
        assertThat(category.getCode()).isEqualTo("NEW_CODE");
    }
}
