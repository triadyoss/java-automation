package triady.generator.core.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {
  private static final ObjectMapper json = new ObjectMapper();

  static {
    json.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
  }

  public static String toString(Object value){
    try {
      return json.writerWithDefaultPrettyPrinter().writeValueAsString(value);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
