package triady.generator.cache.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.jsonschema.interfaces.JsonSchemaSpecification;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;
import triady.generator.schema.interfaces.SchemaMetadata;

@Getter
public class CacheMetadata extends Metadata<CacheMetadata> {
  private final ModuleMetadata module;
  private final SchemaMetadata schema;
  private final JsonSchemaSpecification cacheSchema;

  private CacheMetadata(ModuleMetadata module, SchemaMetadata schema, JsonSchemaSpecification cacheSchema, Name name, Description description) {
    super(Collection.CACHE, name, description);
    this.module = module;
    this.schema = schema;
    this.cacheSchema = cacheSchema;
  }

  public static CacheMetadata create(Name name){
    return create(null, null, null, name, null);
  }

  public static CacheMetadata create(ModuleMetadata module, SchemaMetadata schema, JsonSchemaSpecification cacheSchema, Name name, Description description) {
    return new CacheMetadata(module, schema, cacheSchema, name, description);
  }

  public boolean schemaBased(){
    return this.schema != null;
  }
}
