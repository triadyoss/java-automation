package triady.generator.core.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import compozitor.processor.core.interfaces.TypeModel;
import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSchema extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "json-schemas/");

  @Getter
  private final String content;

  private JsonSchema(Id id, String content) {
    super(PATH.toString(), id);
    this.content = content;
  }

  public static JsonSchema create(TypeModel typeModel){
    Id id = Id.create(typeModel.getQualifiedName());
    return new JsonSchema(id, jsonSchema(typeModel));
  }

  private static String jsonSchema(TypeModel typeModel) {
    try {
      Class schemaClass = Class.forName(typeModel.getQualifiedName());

      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

      JsonSchemaGenerator schemaGenerator = new JsonSchemaGenerator(mapper);
      com.fasterxml.jackson.module.jsonSchema.JsonSchema schema = schemaGenerator.generateSchema(schemaClass);

      return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
