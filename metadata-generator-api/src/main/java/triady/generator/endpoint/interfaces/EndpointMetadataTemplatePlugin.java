package triady.generator.endpoint.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.template.core.interfaces.TemplateEngine;

public abstract class EndpointMetadataTemplatePlugin implements TemplatePlugin {

  @Override
  public final void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/endpoint.atf"));
    template.setFileName(Filename.create("${Endpoint.Id}.yml"));
    template.setNamespace(Namespace.create("${Endpoint.Namespace}"));
  }

  @Override
  public final CodeGenerationCategory category() {
    return EndpointMetadataCategory.INSTANCE;
  }
}