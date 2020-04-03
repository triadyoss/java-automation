package triady.generator.core.interfaces;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class Name {
  private final String value;

  @Override
  public String toString() {
    return this.value;
  }
}
