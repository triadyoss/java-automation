package triady.generator.cache.interfaces;

import compozitor.processor.core.interfaces.TypeModel;
import triady.generator.core.interfaces.Id;
import triady.generator.core.interfaces.JsonSchemaSpecification;

public class CacheSchemaFactory {

  public static JsonSchemaSpecification create(TypeModel key, TypeModel value){
    Id id = Id.create(key.getQualifiedName(), value.getQualifiedName());
    return new JsonSchemaAdapter(id).key(key).value(value);
  }

  static class JsonSchemaAdapter extends JsonSchemaSpecification {
    JsonSchemaAdapter(Id id) {
      super(id);
    }

    public JsonSchemaAdapter key(TypeModel keyModel){
      this.put("key", keyModel.getQualifiedName());
      return this;
    }

    public JsonSchemaAdapter value(TypeModel valueModel){
      this.put(VALUE, valueModel.getQualifiedName());
      return this;
    }
  }
}
