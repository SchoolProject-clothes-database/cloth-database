package se.iths.clothdatabase.service;

import org.springframework.stereotype.Service;
import se.iths.clothdatabase.entity.CartEntity;
import se.iths.clothdatabase.repository.CartRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CartService {

    CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartEntity createCart(CartEntity cartEntity) {
        return cartRepository.save(cartEntity);
    }

    public void deleteCart(Long id) {
        CartEntity foundCart = cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        cartRepository.deleteById(foundCart.getId());
    }

    public Optional<CartEntity> findCartById(Long id) {
        return cartRepository.findById(id);
    }

    public Iterable<CartEntity> findAllCarts() {
        return cartRepository.findAll();
    }

}
