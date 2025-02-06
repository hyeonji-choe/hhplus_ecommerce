package kr.hhplus.be.server.application.product;

import kr.hhplus.be.server.api.model.FamousProductResult;
import kr.hhplus.be.server.domain.order.repository.OrderItemRepository;
import kr.hhplus.be.server.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@EnableCaching
public class ProductServiceImplTest {
    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        cacheManager = new ConcurrentMapCacheManager("POPULAR_ITEM");
    }

    @Test
    void testFindTop5OrderProducts_Cacheable() {
        // First call - hit the repository
        List<FamousProductResult> result1 = productService.findTop5OrderProducts();
        // Second call - use cache
        List<FamousProductResult> result2 = productService.findTop5OrderProducts();

        assertThat(result1).hasSize(result2.size());

        verify(orderItemRepository, times(1)).findTop5OrderProducts();
    }

    @Test
    void testCacheEviction() {
        // Populate cache
        productService.findTop5OrderProducts();
        assertThat(cacheManager.getCache("POPULAR_ITEM").get("findTop5OrderProducts")).isNotNull();

        // Evict cache
        productService.evictTop5Products();
        assertThat(cacheManager.getCache("POPULAR_ITEM").get("findTop5OrderProducts")).isNull();
    }
}