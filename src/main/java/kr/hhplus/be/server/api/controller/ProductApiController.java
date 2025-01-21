package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.ProductApi;
import kr.hhplus.be.server.api.model.FamousProductResult;
import kr.hhplus.be.server.api.model.ProductResult;
import kr.hhplus.be.server.application.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductApiController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<List<FamousProductResult>> searchFamousProducts(String date) {
        return ResponseEntity.ok(productService.findTop5OrderProducts());
    }

    @Override
    public ResponseEntity<List<ProductResult>> searchProducts(int page, int rowsPerPage) {
        Page<ProductResult> productResults = productService.getProductList(Pageable.ofSize(rowsPerPage).withPage(page));
        return ResponseEntity.ok((List<ProductResult>) productResults);
    }
}
