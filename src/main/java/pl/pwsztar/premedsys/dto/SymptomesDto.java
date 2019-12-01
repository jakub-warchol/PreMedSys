package pl.pwsztar.premedsys.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class SymptomesDto implements Serializable {
  String symptomeName;
}
