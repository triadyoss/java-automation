package triady.generator.cache.interfaces;

import compozitor.engine.core.interfaces.TemplatePlugin;
import compozitor.engine.core.interfaces.TypeModelPlugin;
import compozitor.generator.core.interfaces.CodeGenerationCategory;
import compozitor.generator.core.interfaces.Filename;
import compozitor.generator.core.interfaces.Namespace;
import compozitor.generator.core.interfaces.TemplateMetadata;
import compozitor.generator.core.interfaces.TemplateRepository;
import compozitor.processor.core.interfaces.TypeModel;
import compozitor.template.core.interfaces.TemplateEngine;

public abstract class CacheMetadataTemplatePlugin implements TemplatePlugin {

  @Override
  public final void accept(TemplateEngine templateEngine, TemplateRepository templateRepository) {
    final TemplateMetadata template = templateRepository.addRegularResourceTemplate();
    template.setTemplate(templateEngine.getTemplate("templates/triady/cache.atf"));
    template.setFileName(Filename.create("${Cache.Id}.yml"));
    template.setNamespace(Namespace.create("${Cache.Namespace}"));
  }

  @Override
  public final CodeGenerationCategory category() {
    return CacheMetadataCategory.INSTANCE;
  }
}