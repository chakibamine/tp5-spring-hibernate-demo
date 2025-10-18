package controllers;

import dao.IDao;
import entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    @Qualifier("categoryDao")
    private IDao<Category> categoryDao;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryDao.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Category category = categoryDao.findById(id);
        model.addAttribute("category", category);
        return "categories/form";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category) {
        categoryDao.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        Category category = categoryDao.findById(id);
        if (category != null) {
            categoryDao.delete(category);
        }
        return "redirect:/categories";
    }
}
