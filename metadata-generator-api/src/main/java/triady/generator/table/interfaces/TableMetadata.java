package triady.generator.table.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;
import triady.generator.type.interfaces.TypeSpecification;

@Getter
public class TableMetadata extends Metadata<TableMetadata> {
  private final ModuleMetadata module;
  private final TypeSpecification typeSpecification;

  private TableMetadata(ModuleMetadata module, TypeSpecification typeSpecification, Name name, Description description) {
    super(Collection.TABLE, name, description);
    this.module = module;
    this.typeSpecification = typeSpecification;
  }

  public static TableMetadata create(Name name){
    return create(null, null, name, null);
  }

  public static TableMetadata create(ModuleMetadata module, TypeSpecification typeSpecification, Name name, Description description) {
    return new TableMetadata(module, typeSpecification, name, description);
  }
}
