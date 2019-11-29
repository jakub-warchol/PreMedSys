package pl.pwsztar.premedsys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pwsztar.premedsys.utils.WebPageUtil;


@Controller
@RequestMapping("/")
public class PreMedSysController {

  @RequestMapping(value = "", produces = "text/html")
  @ResponseBody
  public String getDefaultPage() {
    return WebPageUtil.getPage();
  }
}
