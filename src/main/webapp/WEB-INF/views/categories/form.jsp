<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category.id == null ? 'Nouvelle Catégorie' : 'Modifier Catégorie'}</title>
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
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0">
                            <i class="fas fa-${category.id == null ? 'plus' : 'edit'} me-2"></i>
                            ${category.id == null ? 'Nouvelle Catégorie' : 'Modifier Catégorie'}
                        </h4>
                    </div>
                    <div class="card-body">
                        <form action="${category.id == null ? '/categories/save' : '/categories/update'}" method="post">
                            <c:if test="${category.id != null}">
                                <input type="hidden" name="id" value="${category.id}">
                            </c:if>
                            
                            <div class="mb-4">
                                <label for="code" class="form-label">
                                    <i class="fas fa-tag me-1"></i>Code de la Catégorie
                                </label>
                                <input type="text" 
                                       class="form-control" 
                                       id="code" 
                                       name="code" 
                                       value="${category.code}"
                                       placeholder="Ex: ELECTRONICS, CLOTHING, BOOKS..."
                                       required>
                                <div class="form-text">
                                    Utilisez des codes courts et descriptifs (ex: ELECTRONICS, CLOTHING)
                                </div>
                            </div>
                            
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a href="/categories" class="btn btn-secondary me-md-2">
                                    <i class="fas fa-times me-1"></i>Annuler
                                </a>
                                <button type="submit" class="btn btn-success">
                                    <i class="fas fa-save me-1"></i>
                                    ${category.id == null ? 'Créer' : 'Mettre à jour'}
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
