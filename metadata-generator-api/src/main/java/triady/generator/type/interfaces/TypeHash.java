package triady.generator.type.interfaces;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import triady.generator.core.interfaces.Yaml;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TypeHash {
  private final Map<String, Object> type;

  static TypeHash create() {
    return new TypeHash(Maps.newHashMap());
  }

  public void set(String property, Object value) {
    this.type.put(property, value);
  }

  public String toYaml() {
    return Yaml.toString(this.type);
  }
}