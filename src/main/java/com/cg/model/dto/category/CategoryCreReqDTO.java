package com.cg.model.dto.category;

import com.cg.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CategoryCreReqDTO {

    private String title;

    public Category toCategory(){
        return new Category()
                .setId(null)
                .setTitle(title)
                ;
    }
}
