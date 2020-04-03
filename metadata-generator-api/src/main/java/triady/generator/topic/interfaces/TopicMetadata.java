package triady.generator.topic.interfaces;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import triady.generator.core.interfaces.Collection;
import triady.generator.core.interfaces.Description;
import triady.generator.core.interfaces.Metadata;
import triady.generator.core.interfaces.Name;
import triady.generator.schema.interfaces.SchemaMetadata;

@Getter
public class TopicMetadata extends Metadata<TopicMetadata> {
  private static final String NAMESPACE = "metadata._topics";
  private final SchemaMetadata schema;

  private TopicMetadata(SchemaMetadata schema, Name name, Description description) {
    super(Collection.TOPIC, name, description);
    this.schema = schema;
  }

  public static TopicMetadata create(Name name){
    return create(null, name, null);
  }

  public static TopicMetadata create(SchemaMetadata schema, Name name, Description description) {
    return new TopicMetadata(schema, name, description);
  }
}
