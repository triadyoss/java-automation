package triady.generator.core.interfaces;

import compozitor.generator.core.interfaces.Namespace;

import java.nio.file.Paths;

public enum Collection {
  ENDPOINT {
    @Override
    protected String key() {
      return "_endpoints";
    }

    @Override
    public String templateKey() {
      return "Endpoint";
    }
  },
  CACHE {
    @Override
    protected String key() {
      return "_caches";
    }

    @Override
    public String templateKey() {
      return "Cache";
    }
  },
  CONTEXT {
    @Override
    protected String key() {
      return "_contexts";
    }

    @Override
    public String templateKey() {
      return "Context";
    }
  },
  MODULE {
    @Override
    protected String key() {
      return "_modules";
    }

    @Override
    public String templateKey() {
      return "Module";
    }
  },
  SCHEMA {
    @Override
    protected String key() {
      return "_schemas";
    }

    @Override
    public String templateKey() {
      return "Schema";
    }
  },
  TABLE {
    @Override
    protected String key() {
      return "_tables";
    }

    @Override
    public String templateKey() {
      return "Table";
    }
  },
  TOPIC {
    @Override
    protected String key() {
      return "_topics";
    }

    @Override
    public String templateKey() {
      return "Topic";
    }
  };

  public Namespace namespace() {
    return Namespace.create(Paths.get(TriadySettings.DATA_DIRECTORY, "metadata", this.key()));
  }

  protected abstract String key();

  public abstract String templateKey();
}
