package mvc.controller;

import mvc.entity.OrdersEntity;
import mvc.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    OrdersRepository ordersRepository;

    @RequestMapping(value="/myOrders", method = RequestMethod.GET)
    public String showAllOrder(Model model) {
        List<OrdersEntity> ordersEntityList = (List<OrdersEntity>) ordersRepository.findAll();
        model.addAttribute("ordersList", ordersEntityList);

        return "shoppingcart/ordersList";
    }
    @RequestMapping(value = "/ordersDetail/{id}", method = RequestMethod.GET)
    public String showOrdersDetail(Model model, @PathVariable int id) {
        OrdersEntity ordersEntity = ordersRepository.findById(id).get();
        model.addAttribute("ordersDetail", ordersEntity.getOrderDetails());
        return "shoppingcart/ordersDetail";
    }
}
