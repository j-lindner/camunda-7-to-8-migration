package org.camunda.community.converter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.camunda.bpm.model.xml.instance.DomElement;
import org.camunda.community.converter.convertible.Convertible;

public class BpmnDiagramCheckContext {
  private final Set<DomElement> elementsToRemove = new HashSet<>();

  private final Map<DomElement, Map<String, Set<String>>> attributesToRemove = new HashMap<>();

  private final Map<DomElement, Convertible> convertibles = new HashMap<>();

  public Map<DomElement, Convertible> getConvertibles() {
    return convertibles;
  }

  public Set<DomElement> getElementsToRemove() {
    return elementsToRemove;
  }

  public Map<DomElement, Map<String, Set<String>>> getAttributesToRemove() {
    return attributesToRemove;
  }

  public void addAttributeToRemove(DomElement element, String namespaceUri, String localName) {
    attributesToRemove
        .computeIfAbsent(element, e -> new HashMap<>())
        .computeIfAbsent(namespaceUri, n -> new HashSet<>())
        .add(localName);
  }

  public void addConvertible(DomElement element, Convertible convertible) {
    if (convertibles.containsKey(element)) {
      throw new IllegalStateException("There must only be one convertible per element!");
    }
    convertibles.put(element, convertible);
  }
}