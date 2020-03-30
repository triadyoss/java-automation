package triady.generator.topic.interfaces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopicCollection implements Iterable<TopicMetadata> {
  private List<TopicMetadata> topics;

  public TopicCollection() {
    this.topics = new ArrayList<>();
  }

  public void add(TopicMetadata topic){
    this.topics.add(topic);
  }

  public void addAll(Collection<TopicMetadata> topics) {
    this.topics.addAll(topics);
  }

  public Optional<TopicMetadata> contains(TopicMetadata topic){
    if(this.topics.contains(topic)){
      return Optional.of(topic);
    }

    return Optional.empty();
  }

  public String collect(){
    return this.stream().map(topic -> topic.getId().toString()).collect(Collectors.joining(","));
  }

  @Override
  public Iterator<TopicMetadata> iterator() {
    return this.topics.iterator();
  }

  public Stream<TopicMetadata> stream(){
    return this.topics.stream();
  }
}
