package triady.generator.topic.interfaces;

import triady.generator.core.interfaces.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopicCollection implements Iterable<TopicMetadata> {
  private Set<TopicMetadata> topics;

  public TopicCollection() {
    this.topics = new HashSet<>();
  }

  public void add(TopicMetadata topic){
    this.topics.add(topic);
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
