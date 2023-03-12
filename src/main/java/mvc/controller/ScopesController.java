package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class ScopesController {
    @Resource(name = "requestScopedBean")
    HelloMessageGenerator requestScopedBean;

    @Resource(name = "sessionScopedBean")
    HelloMessageGenerator sessionScopedBean;

    @Resource(name = "applicationScopedBean")
    HelloMessageGenerator applicationScopedBean;

    @RequestMapping("/scopes/request")
    public String getRequestScopeMessage(final Model model) {
        model.addAttribute("previousMessage", requestScopedBean.getMessage());
        requestScopedBean.setMessage("Request Good morning!");
        model.addAttribute("currentMessage", requestScopedBean.getMessage());
        return "scopesExample";
    }

    @RequestMapping("/scopes/session")
    public String getSessionScopeMessage(final Model model) {
        model.addAttribute("previousMessageSession", sessionScopedBean.getMessage());
        sessionScopedBean.setMessage("Session Good afternoon!");
        model.addAttribute("currentMessageSession", sessionScopedBean.getMessage());
        return "scopesExample";
    }
    @RequestMapping("/scopes/session2")
    public String getSessionScopeMessage2(final Model model) {
        model.addAttribute("previousMessageSession", sessionScopedBean.getMessage());
        sessionScopedBean.setMessage("Session Good afternoon!");
        model.addAttribute("currentMessageSession", sessionScopedBean.getMessage());
        return "scopesExample";
    }

    @RequestMapping("/scopes/application")
    public String getApplicationScopeMessage(final Model model) {
        model.addAttribute("previousMessageApp", applicationScopedBean.getMessage());
        applicationScopedBean.setMessage("App Good afternoon!");
        model.addAttribute("currentMessageApp", applicationScopedBean.getMessage());
        return "scopesExample";
    }
}