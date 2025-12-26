package com.tancom.service;

import com.tancom.dto.request.ProductRequest;
import com.tancom.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(Long id, ProductRequest request);

    void deleteProduct(Long id);

    ProductResponse patch(Long id, ProductPatchRequest request);
}