package com.jimds.buyers.controller;

import com.jimds.buyers.dto.ProductDTO;
import com.jimds.buyers.exceptions.StandardError;
import com.jimds.buyers.model.Product;
import com.jimds.buyers.service.FileStorageService;
import com.jimds.buyers.service.ProductService;
import com.jimds.buyers.util.FileStorageProperties;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product")
public class ProductControler {
    // TODO: Create an Authentication Handler for post and put request

    //TODO: Create this class
    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${systemAddress}")
    private String address;

    @GetMapping("{id}")
    public ResponseEntity findProduct(@PathVariable Integer id){
        //TODO: Validade if it is a valid it
        try{
            Product product = productService.findById(id);
            product.setImageURL(fileStorageService.getUrl(address,product.getImageURL()));
            return ResponseEntity.ok(product);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new StandardError(HttpStatus.BAD_REQUEST.value(),
                            "Bad Request",
                            e.getMessage(),
                            "/products")
            );

        }
    }
    @PostMapping(value = "")
    public ResponseEntity addProduct(@ModelAttribute ProductDTO productData){
        try {

            Product product = productData.toProduct();
            String fileName = fileStorageService.storeFile(productData.getImageFile());
            product.setImageURL(fileName);
            productService.add(product);

            product.setImageURL(fileStorageService.getUrl(address,fileName));

            System.out.println(product.getImageURL());

            return ResponseEntity.ok(product);
        }
        catch (HttpMessageNotReadableException e) {
            return ResponseEntity.badRequest().body(new StandardError(HttpStatus.BAD_REQUEST.value(),"Bad Request"
                    ,"Required request body is missing","/product"));
        }
        catch (Exception e) {
             return ResponseEntity.badRequest().body(new StandardError(HttpStatus.BAD_REQUEST.value(),"Bad Request"
                    ,"Required request body is missing","/product"));
        }
    }

    @GetMapping("")
    public ResponseEntity getAllProducts(){
        try{
            List<Product> productList = productService.findAll();
            productList.parallelStream().forEach(product -> product.setImageURL(
                    fileStorageService.getUrl(address,product.getImageURL())
            ));
            return ResponseEntity.ok(productList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new StandardError(HttpStatus.BAD_REQUEST.value(),"Bad request",
                    e.getMessage(),"/products"));
        }
    }

    //Skip for now
    @PutMapping("{id}")
    public ResponseEntity updateProduct(@RequestBody Product product){
        try{

        }
        catch (Exception e){

        }
        return ResponseEntity.ok("");
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        try{
            Product product = productService.delete(id);
            fileStorageService.delete(product.getImageURL());
            return ResponseEntity.ok().body(product.getId());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new StandardError(HttpStatus.BAD_REQUEST.value(),"Bad request",
                    "Id passed must be a valid ID","/product"));
        }
    }

}
