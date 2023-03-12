package mvc.controller;

import mvc.entity.CartEntity;
import mvc.entity.OrderDetailsEntity;
import mvc.entity.OrdersEntity;
import mvc.entity.ProductEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrdersRepository;
import mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartControllerScope {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    @Resource(name = "sessionCart")
    List<CartEntity> sessionCart;
    @RequestMapping("/myCart")
    public String getSessionScopeMessage(final Model model) {
        model.addAttribute("cartList", sessionCart);
        return "shoppingcart/cart";
    }

    @RequestMapping(value = "/addToCart/{id}", method = RequestMethod.GET)
    public String addToCart(Model model, @PathVariable int id, HttpServletRequest request) {
        Optional<ProductEntity> optionalEntity = productRepository.findById(id);

        ProductEntity product = new ProductEntity();
        product.setName(optionalEntity.get().getName());
        product.setId(optionalEntity.get().getId());
        product.setPrice(optionalEntity.get().getPrice());
        product.setProductDescription(optionalEntity.get().getProductDescription());

        CartEntity item = new CartEntity();
        item.setProduct(product);
        // count quantity
        CartEntity cartTemp = sessionCart.stream()
                .filter(p -> id == (p.getProduct().getId()))
                .findAny()
                .orElse(null);

        if(cartTemp!=null){
            sessionCart.get(sessionCart.indexOf(cartTemp)).setQuantity(cartTemp.getQuantity() + 1);
        }
        else {
            item.setQuantity(1);
            sessionCart.add(item);
        }

        model.addAttribute("cartList", sessionCart);
        return "shoppingcart/cart";
    }
    @RequestMapping(value = "/removeItem/{id}", method = RequestMethod.GET)
    public String removeItem(Model model, @PathVariable int id, HttpServletRequest request) {
        // Find item in list
        CartEntity item = sessionCart.stream()
                .filter(p -> id == (p.getProduct().getId()))
                .findAny()
                .orElse(null);

        // remove
        sessionCart.remove(item);
        // add to session
        model.addAttribute("cartList", sessionCart);
        return "shoppingcart/cart";
    }

    @RequestMapping(value = "/checkOut", method = RequestMethod.GET)
    public String showCheckOutPage(Model model) {
        model.addAttribute("orders", new OrdersEntity());
        return "shoppingcart/checkout";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public String saveOrder(@Valid @ModelAttribute("orders") OrdersEntity orders, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "shoppingcart/checkout";
        }
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderDate(LocalDate.now());
        ordersEntity.setCustomerAddress(orders.getCustomerAddress());
        ordersEntity.setCustomerName(orders.getCustomerName());
        ordersRepository.save(ordersEntity);

        for (CartEntity cart: sessionCart) {
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            orderDetailsEntity.setOrders(ordersEntity);
            orderDetailsEntity.setProduct(cart.getProduct());
            orderDetailsEntity.setQuantity(cart.getQuantity());
            orderDetailsRepository.save(orderDetailsEntity);
        }

        sessionCart.clear();
        return "redirect:/myOrders";
    }
}
