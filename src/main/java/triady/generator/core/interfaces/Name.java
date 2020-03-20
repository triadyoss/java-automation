package triady.generator.core.interfaces;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(staticName = "create")
@ToString(of = "value")
public class Name {
  private final String value;
}
