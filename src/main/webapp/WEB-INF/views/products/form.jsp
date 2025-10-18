<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${product.id == null ? 'Nouveau Produit' : 'Modifier Produit'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="/">
                <i class="fas fa-database me-2"></i>Spring Hibernate Demo
            </a>
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="/products">
                    <i class="fas fa-box me-1"></i>Produits
                </a>
                <a class="nav-link" href="/categories">
                    <i class="fas fa-tags me-1"></i>Catégories
                </a>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">
                            <i class="fas fa-${product.id == null ? 'plus' : 'edit'} me-2"></i>
                            ${product.id == null ? 'Nouveau Produit' : 'Modifier Produit'}
                        </h4>
                    </div>
                    <div class="card-body">
                        <form action="${product.id == null ? '/products/save' : '/products/update'}" method="post">
                            <c:if test="${product.id != null}">
                                <input type="hidden" name="id" value="${product.id}">
                            </c:if>
                            
                            <div class="mb-3">
                                <label for="name" class="form-label">
                                    <i class="fas fa-tag me-1"></i>Nom du Produit
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="name" 
                                       name="name" 
                                       value="${product.name}"
                                       required>
                            </div>
                            
                            <div class="mb-3">
                                <label for="price" class="form-label">
                                    <i class="fas fa-euro-sign me-1"></i>Prix
                                </label>
                                <div class="input-group">
                                    <input type="number" 
                                           class="form-control" 
                                           id="price" 
                                           name="price" 
                                           value="${product.price}"
                                           step="0.01"
                                           min="0"
                                           required>
                                    <span class="input-group-text">€</span>
                                </div>
                            </div>
                            
                            <div class="mb-4">
                                <label for="category" class="form-label">
                                    <i class="fas fa-tags me-1"></i>Catégorie
                                </label>
                                <select class="form-select" id="category" name="category.id">
                                    <option value="">Sélectionner une catégorie</option>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.id}" 
                                                ${product.category != null && product.category.id == category.id ? 'selected' : ''}>
                                            ${category.code}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="/products" class="btn btn-secondary me-md-2">
                                    <i class="fas fa-times me-1"></i>Annuler
                                </a>
                                <button type="submit" class="btn btn-primary">
                                    <i class="fas fa-save me-1"></i>
                                    ${product.id == null ? 'Créer' : 'Mettre à jour'}
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
