package triady.generator.core.interfaces;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "create")
public class Description {
  private final String value;

  @Override
  public String toString() {
    return this.value;
  }
}
