# Tests Unitaires et d'Intégration - Spring Hibernate Demo

## 📋 Vue d'ensemble

Ce projet inclut une suite complète de tests unitaires et d'intégration utilisant JUnit, Spring Test, Mockito et AssertJ.

## 🧪 Types de Tests

### 1. Tests Unitaires avec Mockito
- **`ProductDaoImplTest`** : Tests unitaires pour ProductDaoImpl avec mocks
- **`CategoryDaoImplTest`** : Tests unitaires pour CategoryDaoImpl avec mocks
- **`ProductControllerTest`** : Tests unitaires pour ProductController avec mocks

### 2. Tests d'Intégration avec Spring
- **`ProductDaoIntegrationTest`** : Tests d'intégration avec base de données H2
- **`TestHibernateConfigTest`** : Tests de configuration Spring

### 3. Tests des Entités
- **`ProductTest`** : Tests des méthodes getter/setter et constructeurs
- **`CategoryTest`** : Tests des méthodes getter/setter et constructeurs

## 🛠️ Technologies Utilisées

- **JUnit 4.13.2** : Framework de tests
- **Spring Test 5.3.22** : Intégration Spring pour les tests
- **Mockito 4.6.1** : Framework de mocking
- **AssertJ 3.23.1** : Assertions fluides
- **H2 Database 2.1.214** : Base de données en mémoire pour les tests

## 🚀 Exécution des Tests

### Exécuter tous les tests
```bash
mvn test
```

### Exécuter une suite spécifique
```bash
mvn test -Dtest=AllTestsSuite
```

### Exécuter un test spécifique
```bash
mvn test -Dtest=ProductDaoImplTest
```

### Exécuter les tests d'intégration uniquement
```bash
mvn test -Dtest=*IntegrationTest
```

## 📊 Couverture des Tests

### Tests Unitaires (Mockito)
- ✅ Création d'entités
- ✅ Suppression d'entités  
- ✅ Mise à jour d'entités
- ✅ Recherche par ID
- ✅ Recherche de toutes les entités
- ✅ Gestion des cas d'erreur

### Tests d'Intégration (Spring + H2)
- ✅ Persistance en base de données
- ✅ Relations entre entités
- ✅ Transactions
- ✅ Configuration Spring
- ✅ Injection de dépendances

### Tests des Contrôleurs
- ✅ Mapping des URLs
- ✅ Retour des vues correctes
- ✅ Redirections
- ✅ Gestion des modèles

## 🔧 Configuration des Tests

### Base de Données de Test (H2)
```properties
# Configuration H2 Database pour les tests
test.datasource.driver-class-name=org.h2.Driver
test.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
test.datasource.username=sa
test.datasource.password=

# Configuration Hibernate pour les tests
test.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
test.jpa.hibernate.ddl-auto=create-drop
test.jpa.show-sql=true
test.jpa.properties.hibernate.format_sql=true
```

### Configuration Spring de Test
- **`TestHibernateConfig`** : Configuration spécifique aux tests
- **`@Transactional`** : Rollback automatique des transactions
- **`@MockitoJUnitRunner`** : Pour les tests unitaires avec mocks
- **`@SpringJUnit4ClassRunner`** : Pour les tests d'intégration

## 📈 Bonnes Pratiques Implémentées

1. **Séparation des responsabilités** : Tests unitaires vs tests d'intégration
2. **Isolation des tests** : Chaque test est indépendant
3. **Données de test** : Utilisation de données cohérentes
4. **Assertions fluides** : Utilisation d'AssertJ pour des tests lisibles
5. **Mocks appropriés** : Simulation des dépendances externes
6. **Configuration dédiée** : Configuration séparée pour les tests

## 🎯 Résultats Attendus

Tous les tests doivent passer avec succès, confirmant que :
- Les DAOs fonctionnent correctement
- Les contrôleurs gèrent les requêtes appropriées
- Les entités sont correctement mappées
- La configuration Spring est valide
- Les relations entre entités fonctionnent
- Les transactions sont gérées correctement
