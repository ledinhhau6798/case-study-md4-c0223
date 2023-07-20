package com.cg.model;

import com.cg.model.dto.product.ProductCreResDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.model.dto.product.ProductUpResDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
@Accessors(chain = true)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private BigDecimal price;


    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;

     @OneToOne
    @JoinColumn(name = "product_avatar_id", nullable = false)
    private ProductAvatar productAvatar;

     public ProductCreResDTO toProductCreResDTO(){
         return new ProductCreResDTO()
                 .setId(id)
                 .setTitle(title)
                 .setPrice(price)
                 .setCategoryTitle(category.getTitle())
                 .setAvatar(productAvatar.toProductAvatarResDTO());

     }


    public ProductDTO toProductDTO() {
         return new ProductDTO()
                 .setId(id)
                 .setTitle(title)
                 .setPrice(price)
                 .setCategory(category.toCategoryDTO())
                 .setAvatar(productAvatar.toProductAvatarDTO())
                 ;
    }

    public ProductUpResDTO toProductUpResDTO() {
         return new ProductUpResDTO()
                 .setId(id)
                 .setTitle(title)
                 .setPrice(price)
                 .setCategoryTitle(category.getTitle())
                 .setAvatar(productAvatar.toProductAvatarResDTO());
    }
}
