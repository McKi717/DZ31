package learnUp.dz19.service.buyer;

import learnUp.dz19.entity.Buyer;
import learnUp.dz19.repository.BuyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;

    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    public List<Buyer> getBuyers() {return buyerRepository.findAll();}
}
