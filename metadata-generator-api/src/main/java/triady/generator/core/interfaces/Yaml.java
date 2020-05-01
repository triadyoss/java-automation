package triady.generator.core.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.*;

public class Yaml {
  private static final ObjectMapper YML = new ObjectMapper(new YAMLFactory().disable(WRITE_DOC_START_MARKER));

  static {
    YML.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    YML.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  public static String toString(Object value){
    try {
      return YML.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
