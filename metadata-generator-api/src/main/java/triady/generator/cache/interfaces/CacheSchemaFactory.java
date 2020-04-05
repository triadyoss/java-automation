package triady.generator.cache.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import compozitor.processor.core.interfaces.TypeModel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.JsonSchema;

import java.util.function.Supplier;

public class CacheSchemaFactory {

  public static JsonSchema create(TypeModel key, TypeModel value){
    JsonSchema keySchema = JsonSchema.create(key);
    JsonSchema valueSchema = JsonSchema.create(value);

    Id id = Id.create(keySchema.getId(), valueSchema.getId());
    return new JsonSchemaAdapter(id, keySchema, valueSchema);
  }

  static class JsonSchemaAdapter extends JsonSchema {
    JsonSchemaAdapter(Id id, JsonSchema keySchema, JsonSchema valueSchema) {
      super(id, new SchemaLoader(keySchema, valueSchema));
    }

    @RequiredArgsConstructor
    static class SchemaLoader implements Supplier<String> {
      private final JsonSchema key;

      private final JsonSchema value;

      @Override
      public String get() {
        try {
          JsonCacheSchema schema = new JsonCacheSchema(this.key.getContent(), this.value.getContent());
          return json.writeValueAsString(schema);
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }

      @RequiredArgsConstructor
      @Getter
      class JsonCacheSchema {
        private final String key;

        private final String value;
      }
    }
  }
}
