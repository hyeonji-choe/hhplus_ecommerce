package kr.hhplus.be.server.application.product;

import kr.hhplus.be.server.api.model.ProductResult;
import kr.hhplus.be.server.common.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResult getProductByProductIdWithLock(Long productId);

    Page<ProductResult> getProductList(Pageable pageable);

    ProductResult decreaseStock(Long productId, Long quantity) throws CustomException;
}
