package controllers;

import dao.IDao;
import entities.Product;
import entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("productDao")
    private IDao<Product> productDao;

    @Autowired
    @Qualifier("categoryDao")
    private IDao<Category> categoryDao;

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "products/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Product product = new Product();
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "products/form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productDao.create(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productDao.findById(id);
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "products/form";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product) {
        productDao.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        Product product = productDao.findById(id);
        if (product != null) {
            productDao.delete(product);
        }
        return "redirect:/products";
    }
}
