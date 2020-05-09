package triady.generator.cache.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;
import triady.generator.schema.interfaces.SchemaMetadata;
import triady.generator.type.interfaces.TypeSpecification;

@Getter
public class CacheMetadata extends Metadata<CacheMetadata> {
  private final ModuleMetadata module;
  private final SchemaMetadata schema;
  private final TypeSpecification type;

  private CacheMetadata(ModuleMetadata module, SchemaMetadata schema,
                        TypeSpecification type, Name name,
                        Description description) {
    super(Collection.CACHE, name, description);
    this.module = module;
    this.schema = schema;
    this.type = type;
  }

  public static CacheMetadata create(Name name){
    return create(null, null, null, name, null);
  }

  public static CacheMetadata create(ModuleMetadata module, SchemaMetadata schema, TypeSpecification type, Name name, Description description) {
    return new CacheMetadata(module, schema, type, name, description);
  }

  public boolean schemaBased(){
    return this.schema != null;
  }
}
