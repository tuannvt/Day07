package mvc.main;

import mvc.configuration.JPAConfig;
import mvc.entity.BookEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import mvc.repository.BookRepository;
import org.springframework.util.StringUtils;

import java.util.List;

public class main {
    public static void main(String[] args) {
//         AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JPAConfig.class);
//         BookRepository bookRepository = applicationContext.getBean("bookRepository", BookRepository.class);
//        List<BookEntity> bookList = (List<BookEntity>) bookRepository.findByNameContainingOrAuthorContaining("Spring","Spring");
//        for (BookEntity b: bookList) {
//            System.out.println(b.toString());
//        }
        String text = null;
        if(StringUtils.isEmpty(text)){
            System.out.println("hihihi");
        }
    }
}
