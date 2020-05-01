package triady.generator.endpoint.interfaces;

import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.module.interfaces.ModuleMetadata;
import triady.generator.type.interfaces.TypeSpecification;

@Getter
public class EndpointMetadata extends Metadata<EndpointMetadata> {
  private final ModuleMetadata module;

  private final EndpointUri uri;

  private final EndpointMethod method;

  private final EndpointStatusCode statusCodes;

  private final EndpointGraphQLSchema graphQLFile;

  private final TypeSpecification requestFile;

  private final TypeSpecification responseFile;

  private final TypeSpecification errorResponseFile;

  private EndpointMetadata(ModuleMetadata module, Name name, Description description, EndpointUri uri, EndpointMethod method,
                           EndpointStatusCode statusCodes, EndpointGraphQLSchema graphQLFile, TypeSpecification requestFile,
                           TypeSpecification responseFile, TypeSpecification errorResponseFile) {
    super(Collection.ENDPOINT, name, description);
    this.module = module;
    this.uri = uri;
    this.method = method;
    this.statusCodes = statusCodes;
    this.graphQLFile = graphQLFile;
    this.requestFile = requestFile;
    this.responseFile = responseFile;
    this.errorResponseFile = errorResponseFile;
  }

  public static EndpointMetadata create(Name name){
    return create(name, null);
  }

  public static EndpointMetadata create(Name name, Description description) {
    return new EndpointMetadata(null, name, description, null, null, null, null,
      null, null, null);
  }

  public static EndpointMetadata create(ModuleMetadata module, Name name, Description description, EndpointUri uri, EndpointMethod method,
                                      EndpointStatusCode statusCodes, EndpointGraphQLSchema graphQLSchema, TypeSpecification requestFile,
                                        TypeSpecification responseFile, TypeSpecification errorResponseFile) {
    return new EndpointMetadata(module, name, description, uri, method,statusCodes, graphQLSchema,
      requestFile, responseFile, errorResponseFile);
  }
}
