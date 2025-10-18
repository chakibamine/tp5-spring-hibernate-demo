package test;

import metier.CategoryDaoImplTest;
import metier.ProductDaoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Suite de tests rapide pour vérifier les corrections des mocks
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    CategoryDaoImplTest.class,
    ProductDaoImplTest.class
})
public class QuickTestSuite {
    // Suite rapide pour tester les corrections des mocks
}
