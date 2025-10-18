<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring Hibernate Demo - Accueil</title>
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

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-8 mx-auto text-center">
                <h1 class="display-4 mb-4">
                    <i class="fas fa-rocket text-primary"></i>
                    Bienvenue dans Spring Hibernate Demo
                </h1>
                <p class="lead mb-5">
                    Une application web professionnelle utilisant Spring MVC et Hibernate pour la gestion des produits et catégories.
                </p>
                
                <div class="row">
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body text-center">
                                <i class="fas fa-box fa-3x text-primary mb-3"></i>
                                <h5 class="card-title">Gestion des Produits</h5>
                                <p class="card-text">Créez, modifiez et supprimez des produits avec leurs prix et catégories.</p>
                                <a href="/products" class="btn btn-primary">
                                    <i class="fas fa-arrow-right me-1"></i>Voir les Produits
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-6 mb-4">
                        <div class="card h-100 shadow-sm">
                            <div class="card-body text-center">
                                <i class="fas fa-tags fa-3x text-success mb-3"></i>
                                <h5 class="card-title">Gestion des Catégories</h5>
                                <p class="card-text">Organisez vos produits en catégories pour une meilleure structure.</p>
                                <a href="/categories" class="btn btn-success">
                                    <i class="fas fa-arrow-right me-1"></i>Voir les Catégories
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="mt-5">
                    <h3 class="mb-3">Technologies Utilisées</h3>
                    <div class="row">
                        <div class="col-md-3 col-6 mb-2">
                            <span class="badge bg-primary fs-6">Spring MVC</span>
                        </div>
                        <div class="col-md-3 col-6 mb-2">
                            <span class="badge bg-success fs-6">Hibernate</span>
                        </div>
                        <div class="col-md-3 col-6 mb-2">
                            <span class="badge bg-info fs-6">MySQL</span>
                        </div>
                        <div class="col-md-3 col-6 mb-2">
                            <span class="badge bg-warning fs-6">JSP</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="bg-light mt-5 py-4">
        <div class="container text-center">
            <p class="text-muted mb-0">
                <i class="fas fa-code me-1"></i>
                Spring Hibernate Demo - Application Web Professionnelle
            </p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
