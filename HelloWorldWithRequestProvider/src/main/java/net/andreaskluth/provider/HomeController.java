package net.andreaskluth.provider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.Locale;

@Controller
public class HomeController {

  @Inject
  Provider<PrototypeBean> provider;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Locale locale, Model model) {
    PrototypeBean bean = provider.get();
    model.addAttribute("hashCode", bean.hashCode());
    model.addAttribute("locale", locale.getDisplayCountry());
    return "home";
  }

}
