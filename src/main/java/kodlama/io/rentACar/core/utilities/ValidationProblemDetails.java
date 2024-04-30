package kodlama.io.rentACar.core.utilities;

import java.util.Map;

import kodlama.io.rentACar.core.utilities.exceptions.ProblemDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetails{
      private Map<String, String> validationErrors;
}
