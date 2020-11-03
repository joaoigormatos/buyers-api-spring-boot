package com.jimds.buyers.service;

import com.jimds.buyers.dto.ProductDTO;
import com.jimds.buyers.exceptions.ProductNotFound;
import com.jimds.buyers.model.Product;
import com.jimds.buyers.repository.ProductRepository;
import com.jimds.buyers.storage.ImgHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IGeneralService<Product>{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id) throws Exception {
        //TODO: Validate this id
        Optional<Product> searchedProduct = productRepository.findById(id);
        if(searchedProduct.isPresent()){
            return searchedProduct.get();
        }
        throw new ProductNotFound("The product with id "+id+" has been not found");
    }

    @Override
    public Product add(Product product) throws Exception {
        //TODO: Allow create product with adding images
        //TODO: Validate the product
        //Todo: Do not allow product with the same title

        return productRepository.save(product);

    }

    @Override
    public Product delete(int id) throws Exception {
        Optional<Product> searchedProduct = productRepository.findById(id);
        if(searchedProduct.isPresent()){
            productRepository.delete(searchedProduct.get());
             return searchedProduct.get();
        }
        throw new ProductNotFound("The product with "+id+" has been not found");
    }

    @Override
    public void updateUser(Product object) throws Exception {
        //TODO: Valid the product obejct
        Optional<Product> searchedProduct = productRepository.findById(object.getId());
        if(searchedProduct.isPresent()){
            productRepository.delete(searchedProduct.get());
            productRepository.save(object);
            return;
        }
        throw new ProductNotFound("Could not update this product because it has not been found at our database!");
    }
}
