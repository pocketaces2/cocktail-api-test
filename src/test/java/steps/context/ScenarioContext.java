package steps.context;

import enums.Context;
import java.util.EnumMap;
import java.util.Map;

/**
 * Used for storing test data between steps, test data is normally specified in the BDD feature files
 */
public class ScenarioContext {

  private final Map<Context, Object> scenarioContext;

  public ScenarioContext(){
    scenarioContext = new EnumMap<>(Context.class);
  }

  public void setContext(Context key, Object value) {
    scenarioContext.put(key, value);
  }

  public <T> T get(Context key){
    return (T) scenarioContext.get(key);
  }
}
