package dab.client.graphic.renderRegistry;

import java.util.HashMap;
import java.util.Map;

import dab.client.graphic.drawable.VariableDrawable;

public class VariableRenderRegistry {

    private static final Map<String, VariableDrawable> renderList = new HashMap<String, VariableDrawable>();
    
    public static void register(String name, VariableDrawable vd) {
        renderList.put(name, vd);
    }
    
    public static VariableDrawable get(String name) {
        return renderList.get(name);
    }
    
}
