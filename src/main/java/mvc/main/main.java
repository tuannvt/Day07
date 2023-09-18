package mvc.main;


import org.springframework.util.StringUtils;

import java.util.List;

public class main {
    public static void main(String[] args) {

        String text = null;

        if(StringUtils.isEmpty(text)){
            System.out.println("text is empty");
        }
        if(text.equals("abc")){
            System.out.println("abc");
        }
        if("abc".equals(text)){
            System.out.println("abc 2");
        }
    }
}
