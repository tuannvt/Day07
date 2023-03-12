package mvc.controller;

import javassist.expr.NewArray;
import mvc.entity.BookDetailsEntity;
import mvc.entity.BookEntity;
import mvc.entity.CategoryEntity;
import mvc.repository.BookRepository;
import mvc.repository.CategoryRepository;
import mvc.valid.BasicInfor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class BookController {
    @Autowired
    @Qualifier("bookRepository")
    BookRepository bookRepository;
    @Autowired
    @Qualifier("categoryRepository")
    CategoryRepository categoryRepository;
    @RequestMapping(value = "/book", method = GET)
    public String showBook(Model model){
        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findAll();
        model.addAttribute("bookList", bookList);

        return "book/home";
    }

    @RequestMapping(value = "/search", method = GET)
    public String search(@RequestParam("searchInput") String searchInput, Model model){
        List<BookEntity> bookEntityList;
        if(searchInput.isEmpty()){
            bookEntityList = (List<BookEntity>) bookRepository.findAll();
        }else{
            bookEntityList = (List<BookEntity>) bookRepository.findByNameContainingOrAuthorContaining(searchInput, searchInput);
        }
        model.addAttribute("bookList", bookEntityList);
        return "book/home";
    }

    @RequestMapping(value = "/newBook", method = GET)
    public String showNewBook(Model model){
        model.addAttribute("book", new BookEntity());
        model.addAttribute("msg","Add a new Book");
        model.addAttribute("action","newBook");
        model.addAttribute("type","insert");
        setCategoryDropDownList(model);
        return "book/book";
    }
    @RequestMapping(value = "/newBook", method = POST, produces="text/plain;charset=UTF-8")
    public String save(@Validated(BasicInfor.class) @ModelAttribute("book") BookEntity book,
                       BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            setCategoryDropDownList(model);
            return "book/book";
        }
        bookRepository.save(book);
        //  model.addAttribute("book", book);
        return "redirect:/";
    }
    @RequestMapping(value = "/edit/{id}", method = GET)
    public String showEditBook(Model model, @PathVariable int id, RedirectAttributes redir){
        Optional<BookEntity> book = bookRepository.findById(id);
        String msg = "Update book information";
        if(!book.isPresent()){
            msg = "This book ID "+ id +" not exist";
            redir.addFlashAttribute("msg",msg);

            return "redirect:/book";
        }
        model.addAttribute("book", book.get());
        model.addAttribute("action","/updateBook");
        model.addAttribute("msg",msg);
        model.addAttribute("type","update");
        setCategoryDropDownList(model);

        return "book/book";
    }

    @RequestMapping(value = "/updateBook", method = POST)
    public String updateBook(@Validated(BasicInfor.class) @ModelAttribute("book") BookEntity book,
                             BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            setCategoryDropDownList(model);
            return "book/book";
        }
        bookRepository.save(book);
      //  model.addAttribute("book", book);
        return "redirect:/book";
    }

    @RequestMapping(value = "/delete/{id}", method = GET)
    public String deleteBook(@PathVariable int id){
        bookRepository.deleteById(id);
        return "redirect:/book";
    }
    private void setCategoryDropDownList(Model model) {
        List<CategoryEntity> categoryEntityList = (List<CategoryEntity>) categoryRepository.findAll();
        if(!categoryEntityList.isEmpty()){
            Map<Integer, String> cateMap = new LinkedHashMap<>();
            for (CategoryEntity e: categoryEntityList) {
                cateMap.put(e.getId(),e.getName());
            }
            model.addAttribute("categoryList", cateMap);
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }

}
