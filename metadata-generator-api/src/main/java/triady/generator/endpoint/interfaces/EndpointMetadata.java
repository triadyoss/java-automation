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

  private final EndpointGraphQLSchema graphQLSchema;

  private final TypeSpecification requestType;

  private final TypeSpecification responseType;

  private final TypeSpecification errorResponseType;

  private EndpointMetadata(ModuleMetadata module, Name name, Description description, EndpointUri uri, EndpointMethod method,
                           EndpointStatusCode statusCodes, EndpointGraphQLSchema graphQLSchema, TypeSpecification requestType,
                           TypeSpecification responseType, TypeSpecification errorResponseType) {
    super(Collection.ENDPOINT, name, description);
    this.module = module;
    this.uri = uri;
    this.method = method;
    this.statusCodes = statusCodes;
    this.graphQLSchema = graphQLSchema;
    this.requestType = requestType;
    this.responseType = responseType;
    this.errorResponseType = errorResponseType;
  }

  public static EndpointMetadata create(Name name) {
    return new EndpointMetadata(null, name, null, null, null, null, null,
      null, null, null);
  }

  public static EndpointMetadata create(ModuleMetadata module, Name name, Description description, EndpointUri uri, EndpointMethod method,
                                        EndpointStatusCode statusCodes, TypeSpecification requestType,
                                        TypeSpecification responseType, TypeSpecification errorResponseType) {
    EndpointGraphQLSchema noSchema = EndpointGraphQLSchema.create();
    return new EndpointMetadata(module, name, description, uri, method, statusCodes,
      noSchema, requestType, responseType, errorResponseType);
  }

  public static EndpointMetadata create(ModuleMetadata module, Name name, Description description, EndpointUri uri,
                                        EndpointGraphQLSchema graphQLSchema) {
    TypeSpecification noType = TypeSpecification.create();
    return new EndpointMetadata(module, name, description, uri, EndpointMethod.POST,
      EndpointStatusCode.create().add(200), graphQLSchema, noType, noType, noType);
  }
}
