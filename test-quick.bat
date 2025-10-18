@echo off
echo ========================================
echo Test Rapide - Corrections des Mocks
echo ========================================
echo.

echo Test des corrections pour CategoryDaoImpl et ProductDaoImpl...
echo.

echo Compilation des tests...
call mvn test-compile
if %errorlevel% neq 0 (
    echo Erreur lors de la compilation des tests
    pause
    exit /b 1
)

echo.
echo Execution des tests corriges...
call mvn test -Dtest=QuickTestSuite
if %errorlevel% neq 0 (
    echo Certains tests echouent encore
    pause
    exit /b 1
)

echo.
echo ========================================
echo Tests corriges avec succes!
echo ========================================
echo.
echo Vous pouvez maintenant executer tous les tests avec:
echo mvn test
echo.
pause
