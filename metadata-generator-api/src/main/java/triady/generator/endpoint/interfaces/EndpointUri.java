package triady.generator.endpoint.interfaces;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class EndpointUri {
  private final String value;

  public String value(){
    return this.value;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
