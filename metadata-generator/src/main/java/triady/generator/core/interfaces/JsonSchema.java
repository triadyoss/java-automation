package triady.generator.core.interfaces;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;
import compozitor.processor.core.interfaces.TypeModel;
import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSchema extends Resource {
  private static final Path PATH = Paths.get(TriadySettings.DATA_DIRECTORY, "json-schemas/");

  @Getter
  private final String targetClassName;

  private JsonSchema(Id id, String targetClassName) {
    super(PATH.toString(), id);
    this.targetClassName = targetClassName;
  }

  public static JsonSchema create(TypeModel typeModel){
    Id id = Id.create(typeModel.getQualifiedName());
    return new JsonSchema(id, typeModel.getQualifiedName());
  }

  public Path getEndpoint() {
    String endpoint = this.getPath().toString().replace("src", "");
    return Paths.get(endpoint);
  }

  public void write(){
    try {
      Path path = this.getPath();
      String content = jsonSchema();
      Files.write(path, content.getBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String jsonSchema() {
    try {
      Class schemaClass = Class.forName(this.targetClassName);

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
