package triady.generator.api.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.GraphQLFile;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.core.interfaces.SwaggerFile;

@Getter
public class ApiMetadata extends Metadata<ApiMetadata> {
  private final GraphQLFile graphQLFile;
  private final SwaggerFile swaggerFile;

  private ApiMetadata(Name name, Description description, GraphQLFile graphQLFile, SwaggerFile swaggerFile) {
    super(Collection.API, name, description);
    this.graphQLFile = graphQLFile;
    this.swaggerFile = swaggerFile;
  }

  public static ApiMetadata create(Name name){
    return create(name, null, null, null);
  }

  public static ApiMetadata create(Name name, Description description, GraphQLFile graphQLFile, SwaggerFile swaggerFile) {
    return create(name, description, graphQLFile, swaggerFile);
  }

  public boolean swaggerBased(){
    return this.swaggerFile != null;
  }

  public boolean graphQLBased(){
    return this.graphQLFile != null;
  }
}
