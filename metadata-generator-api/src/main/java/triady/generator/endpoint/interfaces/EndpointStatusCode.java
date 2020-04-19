package triady.generator.endpoint.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class EndpointStatusCode {
  private final Collection<Integer> values;

  private EndpointStatusCode() {
    this.values = new ArrayList<>();
  }

  public static EndpointStatusCode create(){
    return new EndpointStatusCode();
  }

  public EndpointStatusCode add(Integer statusCode){
    if(statusCode >= 100 && statusCode < 600 ){
      this.values.add(statusCode);
    }
    return this;
  }

  public String value(){
    return this.values.stream().map(statusCode -> statusCode.toString()).collect(Collectors.joining(","));
  }

  public String toString(){
    return this.value();
  }
}
