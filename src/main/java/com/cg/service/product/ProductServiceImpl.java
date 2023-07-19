package com.cg.service.product;


import com.cg.exception.DataInputException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.ProductAvatar;

import com.cg.model.dto.product.ProductCreReqDTO;
import com.cg.model.dto.product.ProductDTO;
import com.cg.repository.CategoryRepository;
import com.cg.repository.ProductAvatarRepository;
import com.cg.repository.ProductRepository;
import com.cg.service.upload.IUploadService;

import com.cg.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtils uploadUtils;

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public void delete(Product product) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<ProductDTO> findAllProductDTO() {
        return productRepository.findAllProductDTO();
    }

    @Override
    public Product create(ProductCreReqDTO productCreReqDTO,Category category) {

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatarRepository.save(productAvatar);

        uploadAndSaveProductImage(productCreReqDTO, productAvatar);

        Product product = productCreReqDTO.toProduct(category);

        product.setProductAvatar(productAvatar);
        productRepository.save(product);


        return product;
    }
//
//    @Override
//    public Product update(Product product, ProductUpReqDTO productUpReqDTO) {
//        CategoryUpReqDTO categoryUpReqDTO =productUpReqDTO.getCategory();
//        Category category = categoryUpReqDTO.toCategory(product.getCategory().getId());
//        categoryRepository.save(category);
//
//        Product productUpdate = productUpReqDTO.toProduct(product.getId(),category);
//        productRepository.save(productUpdate);
//        return productUpdate;
//    }
//
//    @Override
//    public void deleteByIdTrue(Long id) {
//        productRepository.deleteByIdTrue(id);
//    }

    private void uploadAndSaveProductImage(ProductCreReqDTO productCreReqDTO, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(productCreReqDTO.getAvatar(), uploadUtils.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtils.IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            productAvatarRepository.save(productAvatar);

        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại");
        }
    }
}