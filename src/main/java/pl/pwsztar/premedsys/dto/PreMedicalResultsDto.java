package pl.pwsztar.premedsys.dto;

import io.vavr.collection.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PreMedicalResultsDto implements Serializable {
  String diseaseName;
  Long diseaseProbability;
  List<String> recommendations;
}
