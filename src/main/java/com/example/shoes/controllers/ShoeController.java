package com.example.shoes.controllers;

import com.example.shoes.models.Shoes;
import com.example.shoes.services.ShoesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ShoeController {
    private final ShoesService shoesService;

    @GetMapping("/")
    public String products(@RequestParam(name = "category", required = false) String category, Model model) {
        model.addAttribute("products", shoesService.listShoes(category));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Shoes shoe = shoesService.getShoeById(id);
        model.addAttribute("shoe", shoe);
        model.addAttribute("images", shoe.getImages());
        return "shoe-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3, Shoes product) throws IOException {
        shoesService.saveShoe(product, file1, file2, file3);
        return "redirect:/";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        shoesService.deleteShoe(id);
        return "redirect:/";
    }
}
