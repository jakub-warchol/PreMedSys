package pl.pwsztar.premedsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pwsztar.premedsys.domain.DiseaseFacade;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;
import pl.pwsztar.premedsys.utils.WebPageUtil;
import java.util.List;

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
    return diseaseFacade.getDiseases().toJavaList();
  }

  @GetMapping("symptomes")
  @ResponseBody
  public List<SymptomesDto> getAllSymptomes() { return diseaseFacade.getDiseasesSymptomes().toJavaList(); }

  @GetMapping("all")
  @ResponseBody
  public List<DiseasesDto> getDiseasesBySymptomes() {return diseaseFacade.getDiseasesBySymptomesName(io.vavr.collection.List.of("Odksztuszanie plwociny", "Częste oddawanie moczu", "Zamazane lub podwójne widzenie", "Malinowy język")).toJavaList();};
}
