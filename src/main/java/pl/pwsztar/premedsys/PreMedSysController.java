package pl.pwsztar.premedsys;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pwsztar.premedsys.domain.DiseaseFacade;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.PreMedicalResultsDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;
import pl.pwsztar.premedsys.utils.WebPageUtil;
import java.util.List;

@Controller
@RequestMapping("/")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PreMedSysController {

  DiseaseFacade diseaseFacade;

  @Autowired
  PreMedSysController(@Autowired DiseaseFacade diseaseFacade) {
    this.diseaseFacade = diseaseFacade;
  }

  @RequestMapping(value = "", produces = "text/html")
  @ResponseBody
  public String getDefaultPage() {
    return WebPageUtil.getPage();
  }

  @GetMapping("diseases")
  @ResponseBody
  public ResponseEntity<List<DiseasesDto>> getAllDiseases() { return ResponseEntity.ok(diseaseFacade.getDiseases().toJavaList()); }

  @GetMapping("symptomes")
  @ResponseBody
  public ResponseEntity<List<SymptomesDto>> getAllSymptomes() { return ResponseEntity.ok(diseaseFacade.getDiseasesSymptomes().toJavaList()); }

  @PostMapping("recognition")
  public ResponseEntity<List<PreMedicalResultsDto>> getDiseasesBySymptomes(@RequestBody List<String> symptomes) {
    List<PreMedicalResultsDto> results = diseaseFacade.getDiseasesAndRecommendationsBySymptomesName(symptomes).toJavaList();
    return ResponseEntity.ok(results);
  };
}
