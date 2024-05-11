package com.julionborges.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    public List<ProductDTO> listAll() {
        return Product
                .<Product>listAll()
                .stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Optional<Product> productOptional = Product.findByIdOptional(id);

        if(productOptional.isEmpty())
            throw new NotFoundException("Product not found");

        Product product = productOptional.get();
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Transactional
    public ProductDTO newProduct(ProductDTO newProduct) {
        Product product = new Product(newProduct.id(), newProduct.name(), newProduct.price());
        product.setId(null);
        product.persist();

        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    @Transactional
    public ProductDTO updateProduct(ProductDTO updateProduct) {
        Optional<Product> optionalProduct = Product.findByIdOptional(updateProduct.id());

        if(optionalProduct.isEmpty())
            throw new NotFoundException("Produto não encontrado");

        Product product = optionalProduct.get();
        product.setName(updateProduct.name());
        product.setPrice(updateProduct.price());
        product.persist();

        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }
}