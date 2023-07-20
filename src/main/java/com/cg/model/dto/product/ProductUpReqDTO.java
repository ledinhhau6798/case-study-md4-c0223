package com.cg.model.dto.product;

import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;
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
public class ProductUpReqDTO {

    private String title;
    private String price;
    private String categoryId;
    private MultipartFile avatar;
    private ProductAvatar productAvatar;

    public Product toProduct(Long id,Category category) {
        return new Product()
                .setId(id)
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setCategory(category)
                ;
    }

    public ProductCreReqDTO toProductCreReqDTO () {
        return new ProductCreReqDTO()
                .setTitle(title)
                .setPrice(price)
                .setCategoryId(Long.parseLong(categoryId))
                .setAvatar(avatar)
                ;
    }

    public Product toProductChangeImage(Category category) {
        return new Product()
                .setTitle(title)
                .setPrice(BigDecimal.valueOf(Long.parseLong(price)))
                .setCategory(category)
                ;
    }
}
