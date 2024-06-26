package com.julionborges.cart.model;

import com.julionborges.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "cartSequence",
            sequenceName = "cart_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartSequence")
    private Long id;
    @Column(name = "total_price")
    private Float totalPrice;
    @Column(name = "cart_status")
    private String cartStatus;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<CartProduct> cartProductList;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Cart() {
    }

    public Cart(Long id, Float totalPrice, String cartStatus, List<CartProduct> cartProductList, Long userId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.cartStatus = cartStatus;
        this.cartProductList = cartProductList;
        this.user = new User(userId, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(String cartStatus) {
        this.cartStatus = cartStatus;
    }

    public List<CartProduct> getCartProductList() {
        return cartProductList;
    }

    public void setCartProductList(List<CartProduct> cartProductList) {
        this.cartProductList = cartProductList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
