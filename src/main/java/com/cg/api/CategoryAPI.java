package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.dto.category.*;
import com.cg.service.category.ICategoryService;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ValidateUtils validateUtils;

    @GetMapping
    public ResponseEntity<?> findAllCategory(){
        List<CategoryDTO> categoryDTOS = categoryService.findCategoriesDTO();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getById(@PathVariable Long categoryId){
        Category category = categoryService.findById(categoryId).orElseThrow(() -> {
            throw new DataInputException("Mã khách hàng không tồn tại");
        });

        CategoryDTO categoryDTO = category.toCategoryDTO();
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreReqDTO categoryCreReqDTO){
        CategoryCreResDTO categoryCreResDTO = categoryService.createCategory(categoryCreReqDTO);
        return new ResponseEntity<>(categoryCreResDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/edit/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") String categoryIdStr, @RequestBody CategoryUpReqDTO categoryUpReqDTO){
        if (!validateUtils.isNumberValid(categoryIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã sản phẩm không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Long categoryId = Long.parseLong(categoryIdStr);
         categoryService.findById(categoryId).orElseThrow(() -> {
           throw new DataInputException("Mã sản phẩm không tồn tại");
        });
        CategoryUpResDTO categoryUpdate = categoryService.updateCategory(categoryId,categoryUpReqDTO);
        return new ResponseEntity<>(categoryUpdate,HttpStatus.CREATED);
    }
    @PatchMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") String categoryIdStr){
        if (!validateUtils.isNumberValid(categoryIdStr)) {
            throw new DataInputException("Mã sản phẩm không hợp lệ");
        }
        Long categoryId = Long.parseLong(categoryIdStr);
        Category category = categoryService.findById(categoryId).orElseThrow(() -> {
           throw new DataInputException("Mã sản phầm không tồn tại ");
        });
        categoryService.deleteByIdTrue(category);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
