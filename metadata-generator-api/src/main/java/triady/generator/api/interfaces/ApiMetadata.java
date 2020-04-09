package triady.generator.api.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;

@Getter
public class ApiMetadata extends Metadata<ApiMetadata> {

  private ApiMetadata(Name name, Description description) {
    super(Collection.API, name, description);
  }

  public static ApiMetadata create(Name name){
    return create(name, null);
  }

  public static ApiMetadata create(Name name, Description description) {
    return create(name, description);
  }
}
