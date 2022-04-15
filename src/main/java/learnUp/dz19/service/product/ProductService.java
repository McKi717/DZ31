package learnUp.dz19.service.product;

import learnUp.dz19.entity.Product;
import learnUp.dz19.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    public void updateProduct (Product product){
        try {
            productRepository.save(product);
        }
        catch (OptimisticLockException e){
            log.warn("Optimistic lock for", product.getId());
        }
    }
}
