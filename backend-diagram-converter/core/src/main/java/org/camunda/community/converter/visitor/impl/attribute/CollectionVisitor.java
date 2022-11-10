package org.camunda.community.converter.visitor.impl.attribute;

import org.camunda.community.converter.BpmnDiagramCheckResult.Severity;
import org.camunda.community.converter.DomElementVisitorContext;
import org.camunda.community.converter.ExpressionUtil;
import org.camunda.community.converter.convertible.AbstractActivityConvertible;
import org.camunda.community.converter.visitor.AbstractSupportedAttributeVisitor;

public class CollectionVisitor extends AbstractSupportedAttributeVisitor {
  @Override
  public String attributeLocalName() {
    return "collection";
  }

  @Override
  protected String visitSupportedAttribute(DomElementVisitorContext context, String attribute) {
    String inputCollection = ExpressionUtil.transform(attribute, true).orElse(attribute);
    context.addConversion(
        AbstractActivityConvertible.class,
        AbstractActivityConvertible::initializeLoopCharacteristics);
    context.addConversion(
        AbstractActivityConvertible.class,
        conversion ->
            conversion
                .getBpmnMultiInstanceLoopCharacteristics()
                .getZeebeLoopCharacteristics()
                .setInputCollection(inputCollection));
    context.addMessage(
        Severity.TASK,
        "Collecting results in a multi instance is now natively possible with Zeebe. Please review");
    return "Please review input collection expression '" + inputCollection + "'";
  }
}