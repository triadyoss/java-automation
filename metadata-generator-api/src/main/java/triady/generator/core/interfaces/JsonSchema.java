package triady.generator.core.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import triady.generator.core.interfaces.JsonSchemaSpecification.SchemaProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSchema {
  private static final ObjectMapper json = new ObjectMapper();

  static {
    json.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
  }

  public String generate(JsonSchemaSpecification schemaSpecification) {
    List<SchemaProperty> properties = schemaSpecification.getProperties();

    if(properties.size() == 1){
      SchemaProperty property = properties.get(0);
      return this.toString(property.getValue());
    }

    Map<String, com.fasterxml.jackson.module.jsonSchema.JsonSchema> schemaMap = new HashMap<>();
    properties.forEach(property -> {
      schemaMap.put(property.getKey(), this.toJsonSchema(property.getValue()));
    });

    return Json.toString(schemaMap);
  }

  private String toString(String targetClassName) {
    return Json.toString(this.toJsonSchema(targetClassName));
  }

  private com.fasterxml.jackson.module.jsonSchema.JsonSchema toJsonSchema(String targetClassName) {
    try{
      Class<?> targetClass = Class.forName(targetClassName);
      JsonSchemaGenerator schemaGenerator = new JsonSchemaGenerator(json);
      return schemaGenerator.generateSchema(targetClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
