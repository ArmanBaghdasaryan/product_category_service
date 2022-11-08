package com.example.product_category_service.service;

import com.example.product_category_service.entity.Product;
import com.example.product_category_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CurrencyService currencyService;

    public List<Product> findAll() {
        List<Product> all = productRepository.findAll();
        if (!all.isEmpty()) {
            HashMap<String, String> currencies = currencyService.getUsdCurrency();
            double usdCurrency = Double.parseDouble(currencies.get("USD"));
            if (usdCurrency > 0) {
                for (Product product : all) {
                    double price = product.getPrice() / usdCurrency;
                    DecimalFormat df = new DecimalFormat("#.##");
                    product.setPrice(Double.parseDouble(df.format(price)));
                }
            }
        }
        return all;
    }

    public Product save(Product product) {
        if (product == null) {
            throw new RuntimeException("Product can't be null");
        }
        return productRepository.save(product);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    public List<Product> getProductsByCategoryId(int id) {
        return productRepository.findProductByCategoryId(id);
    }

    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }
}
