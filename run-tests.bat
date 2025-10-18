@echo off
echo ========================================
echo Spring Hibernate Demo - Tests Unitaires
echo ========================================
echo.

echo Compilation du projet...
call mvn clean compile test-compile
if %errorlevel% neq 0 (
    echo Erreur lors de la compilation
    pause
    exit /b 1
)

echo.
echo Execution des tests...
call mvn test
if %errorlevel% neq 0 (
    echo Erreur lors de l'execution des tests
    pause
    exit /b 1
)

echo.
echo ========================================
echo Tous les tests ont ete executes avec succes!
echo ========================================
pause
