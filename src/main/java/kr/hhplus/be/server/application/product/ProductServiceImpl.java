package kr.hhplus.be.server.application.product;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.FamousProductResult;
import kr.hhplus.be.server.api.model.ProductResult;
import kr.hhplus.be.server.api.model.TopOrderProduct;
import kr.hhplus.be.server.common.exception.CustomException;
import kr.hhplus.be.server.domain.order.repository.OrderItemRepository;
import kr.hhplus.be.server.domain.product.entity.Product;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public ProductResult getProductByProductIdWithLock(Long productId) {
        Product product = productRepository.findByProductIdWithLock(productId);
        if (ObjectUtils.isEmpty(product)) throw new EntityNotFoundException("Product not found.");
        return ProductResult.toResult(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResult> getProductList(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        List<ProductResult> productResults = products.getContent().stream()
                .map(product -> {
                    return ProductResult.toResult(product);
                }).toList();

        return new PageImpl<>(productResults, pageable, products.getTotalElements());
    }

    @Transactional
    public ProductResult decreaseStock(Long productId, Long quantity) throws CustomException {
        Product product = productRepository.findByProductIdWithLock(productId);
        product.decreaseQuantity(quantity);

        Product savedStock = productRepository.save(product);

        return ProductResult.toResult(savedStock);
    }

    @Transactional(readOnly = true)
    public List<FamousProductResult> findTop5OrderProducts() {
        List<TopOrderProduct> topOrderProductList = orderItemRepository.findTop5OrderProducts();

        return topOrderProductList.stream().map(p -> {
            Product product = productRepository.findByProductId(p.getProductId());
            return FamousProductResult.toResult(product, p.getTotalQuantity());
        }).toList();
    }
}
