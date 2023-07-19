package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductCreResDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){

        List<ProductDTO> productDTOS = productService.findAllProductDTO();


        return new ResponseEntity<>(productDTOS,HttpStatus.OK);

    }


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductCreReqDTO productCreReqDTO){

        Category category = categoryService.findById(productCreReqDTO.getCategoryId()).orElseThrow(()->{
           throw new DataInputException("Danh mục không tồn tại");
        });

        Product product = productService.create(productCreReqDTO,category);
        ProductCreResDTO productCreResDTO = product.toProductCreResDTO();
        return new ResponseEntity<>(productCreResDTO,HttpStatus.CREATED);
    }
}
