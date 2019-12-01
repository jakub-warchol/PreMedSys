package pl.pwsztar.premedsys;

import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pwsztar.premedsys.domain.DiseaseFacade;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;
import pl.pwsztar.premedsys.utils.WebPageUtil;


@Controller
@RequestMapping("/")
public class PreMedSysController {

  @Autowired
  DiseaseFacade diseaseFacade;

  @RequestMapping(value = "", produces = "text/html")
  @ResponseBody
  public String getDefaultPage() {
    return WebPageUtil.getPage();
  }

  @GetMapping("diseases")
  @ResponseBody
  public List<DiseasesDto> getAll() {
    return diseaseFacade.getDiseases();
  }

  @GetMapping("symptomes")
  @ResponseBody
  public List<SymptomesDto> getAllSymptomes() {
    return diseaseFacade.getDiseasesSymptomes();
  }
}
