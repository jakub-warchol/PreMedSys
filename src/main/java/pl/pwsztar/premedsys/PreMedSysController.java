package pl.pwsztar.premedsys;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pl.pwsztar.premedsys.domain.DiseaseFacade;
import pl.pwsztar.premedsys.dto.DiseasesDto;
import pl.pwsztar.premedsys.dto.PreMedicalResultsDto;
import pl.pwsztar.premedsys.dto.SymptomesDto;
import pl.pwsztar.premedsys.utils.WebPageUtil;
import java.util.List;

@RestController
@RequestMapping("/")
@Api(value="premedsys", description="API for premedical diseases recognition")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PreMedSysController {

  DiseaseFacade diseaseFacade;

  @Autowired
  PreMedSysController(@Autowired DiseaseFacade diseaseFacade) {
    this.diseaseFacade = diseaseFacade;
  }

  @GetMapping(value = "", produces = "text/html")
  @ApiOperation(value = "Get main server page content.")
  @ResponseBody
  public String getDefaultPage() {
    return WebPageUtil.getPage();
  }

  @GetMapping("diseases")
  @ApiOperation(value = "Get polish and latin name of all recognizing by system diseases.")
  @ResponseBody
  public ResponseEntity<List<DiseasesDto>> getAllDiseases() { return ResponseEntity.ok(diseaseFacade.getDiseases().toJavaList()); }

  @GetMapping("symptomes")
  @ApiOperation(value = "Get all polish name of recognizing disease symptomes.")
  @ResponseBody
  public ResponseEntity<List<SymptomesDto>> getAllSymptomes() { return ResponseEntity.ok(diseaseFacade.getDiseasesSymptomes().toJavaList()); }

  @PostMapping("recognition")
  @ApiOperation(value = "Get list of diseases name, probability (based on pointed symptomes) and premedical recommendation.")
  public ResponseEntity<List<PreMedicalResultsDto>> getDiseasesBySymptomes(@RequestBody List<String> symptomes) {
    List<PreMedicalResultsDto> results = diseaseFacade.getDiseasesAndRecommendationsBySymptomesName(symptomes).toJavaList();
    return ResponseEntity.ok(results);
  };
}
