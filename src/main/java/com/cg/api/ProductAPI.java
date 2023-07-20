package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.product.*;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ValidateUtils validateUtils;

    @GetMapping
    public ResponseEntity<?> getAllProduct(){

        List<ProductDTO> productDTOS = productService.findAllProductDTO();


        return new ResponseEntity<>(productDTOS,HttpStatus.OK);

    }

        @GetMapping("/{productId}")
    public ResponseEntity<?> getById(@PathVariable Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> {
            throw new DataInputException("Mã khách hàng không tồn tại");
        });
        ProductDTO productDTO = product.toProductDTO();
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
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

    @PatchMapping("edit/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable("productId") String productIdStr, @ModelAttribute ProductUpReqDTO productUpReqDTO){
       if (!validateUtils.isNumberValid(productIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã Sản phẩm không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
            Long productId =Long.parseLong(productIdStr);
        Optional<Product> productOptional = productService.findById(productId);
       Category category = categoryService.findById(productUpReqDTO.getCategoryId()).orElseThrow(() -> {
            throw new DataInputException("Danh mục không tồn tại");
        });

        Product product =productService.findById(productId).orElseThrow(() -> {
           throw new DataInputException("Mã sản phẩm không tồn tại");
        });

        Product productUpdate =productService.update(productOptional.get().getId(),productUpReqDTO, category);
        ProductUpResDTO productUpResDTO =productUpdate.toProductUpResDTO();

        return new ResponseEntity<>(productUpResDTO,HttpStatus.OK);
    }
}
