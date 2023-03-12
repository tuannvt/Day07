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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    @RequestMapping(value = "/myCart2", method = RequestMethod.GET)
    public String showCartList(Model model, HttpSession session) {
        // Get session
        List<CartEntity> cartList = (List<CartEntity>) session.getAttribute("cartList");
        if (cartList == null) {
            cartList = new ArrayList<>();
            model.addAttribute("cartList", cartList);
        }

        return "shoppingcart/cart";
    }
    @RequestMapping(value = "/addToCart2/{id}", method = RequestMethod.GET)
    public String addToCart(Model model, @PathVariable int id, HttpServletRequest request) {

        // Get data from session
        List<CartEntity> cartList = (List<CartEntity>) request.getSession().getAttribute("cartList");
        if (cartList == null) {
            cartList = new ArrayList<>();
            request.getSession().setAttribute("cartList", cartList);
        }

        List<CartEntity> resultList = cartList;
        Optional<ProductEntity> optionalEntity = productRepository.findById(id);

        ProductEntity product = new ProductEntity();
        product.setName(optionalEntity.get().getName());
        product.setId(optionalEntity.get().getId());
        product.setPrice(optionalEntity.get().getPrice());
        product.setProductDescription(optionalEntity.get().getProductDescription());

        CartEntity item = new CartEntity();
        item.setProduct(product);

        // count quantity
        CartEntity cartTemp = cartList.stream()
                .filter(p -> id == (p.getProduct().getId()))
                .findAny()
                .orElse(null);

        if(cartTemp!=null){
            cartList.get(cartList.indexOf(cartTemp)).setQuantity(cartTemp.getQuantity() + 1);
        }
        else {
            item.setQuantity(1);
            cartList.add(item);
        }

        request.getSession().setAttribute("cartList", cartList);
        model.addAttribute("cartList", cartList);
        return "shoppingcart/cart";
    }
    @RequestMapping(value = "/removeItem2/{id}", method = RequestMethod.GET)
    public String removeItem(Model model, @PathVariable int id, HttpServletRequest request) {
        // Get data from session
        List<CartEntity> cartList = (List<CartEntity>) request.getSession().getAttribute("cartList");
        if (cartList == null) {
            cartList = new ArrayList<>();
            request.getSession().setAttribute("cartList", cartList);
        }

        // Find item in list
        CartEntity item = cartList.stream()
                .filter(p -> id == (p.getProduct().getId()))
                .findAny()
                .orElse(null);

        // remove
        cartList.remove(item);

        // add to session
        request.getSession().setAttribute("cartList", cartList);
        model.addAttribute("cartList", cartList);

        return "shoppingcart/cart";
    }
    @RequestMapping(value = "/checkOut2", method = RequestMethod.GET)
    public String showCheckOutPage(Model model) {
        model.addAttribute("orders", new OrdersEntity());
        return "shoppingcart/checkout";
    }

    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "/checkOut2", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("orders") OrdersEntity orders, Model model, HttpServletRequest request) {

        List<CartEntity> cartList = (List<CartEntity>) request.getSession().getAttribute("cartList");
        OrdersEntity ordersEntity = new OrdersEntity();
        ordersEntity.setOrderDate(LocalDate.now());
        ordersEntity.setCustomerAddress(orders.getCustomerAddress());
        ordersEntity.setCustomerName(orders.getCustomerName());
        ordersRepository.save(ordersEntity);

        for (CartEntity cart: cartList) {
            OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
            orderDetailsEntity.setOrders(ordersEntity);
            orderDetailsEntity.setProduct(cart.getProduct());
            orderDetailsEntity.setQuantity(cart.getQuantity());
            orderDetailsRepository.save(orderDetailsEntity);
        }
        request.getSession().removeAttribute("cartList");

        return "redirect:/myOrders";
    }

}
