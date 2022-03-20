package se.iths.clothdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.clothdatabase.entity.CartEntity;
import se.iths.clothdatabase.service.CartService;

import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping()
    public ResponseEntity<CartEntity> createCart(@RequestBody CartEntity cart) {
        CartEntity createdCart = cartService.createCart(cart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<CartEntity>> findCartById(@PathVariable Long id) {
        Optional<CartEntity> foundCart = cartService.findCartById(id);
        return new ResponseEntity<>(foundCart, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Iterable<CartEntity>> findAllCart() {
        Iterable<CartEntity> allCart = cartService.findAllCarts();
        return new ResponseEntity<>(allCart, HttpStatus.OK);
    }

}
