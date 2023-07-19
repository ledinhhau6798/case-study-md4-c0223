package com.cg.model.dto.product;

import com.cg.model.Category;
import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductCreReqDTO {
    private String title;
    private String price;
    private Long categoryId;
    private MultipartFile avatar;

    public Product toProduct(Category category) {
        return new Product()
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setCategory(category)
                ;
    }
}
