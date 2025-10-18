package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import config.TestHibernateConfigTest;
import controllers.ProductControllerTest;
import entities.CategoryTest;
import entities.ProductTest;
import integration.ProductDaoIntegrationTest;
import metier.CategoryDaoImplTest;
import metier.ProductDaoImplTest;

/**
 * Suite de tests complète pour l'application Spring Hibernate Demo
 * 
 * Cette suite exécute tous les tests dans l'ordre suivant :
 * 1. Tests des entités (Product, Category)
 * 2. Tests unitaires des DAOs avec Mockito
 * 3. Tests des contrôleurs
 * 4. Tests d'intégration avec Spring et H2
 * 5. Tests de configuration Spring
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    // Tests des entités
    ProductTest.class,
    CategoryTest.class,
    
    // Tests unitaires des DAOs
    ProductDaoImplTest.class,
    CategoryDaoImplTest.class,
    
    // Tests des contrôleurs
    ProductControllerTest.class,
    
    // Tests d'intégration
    ProductDaoIntegrationTest.class,
    
    // Tests de configuration
    TestHibernateConfigTest.class
})
public class AllTestsSuite {
    // Cette classe sert uniquement de conteneur pour la suite de tests
    // Aucune méthode de test ici
}
