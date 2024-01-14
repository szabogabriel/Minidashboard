package com.github.szabogabriel.minidashboard.util;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.szabogabriel.minidashboard.data.gui.renderable.AttributeList;
import com.github.szabogabriel.minidashboard.data.gui.renderable.RenderableObject;

public class JsonToObjecRendererUtil {

    public static RenderableObject toRenderableObject(JSONObject json) {
        return toRenderableObject(json, 0);
    }

    private static RenderableObject toRenderableObject(JSONObject json, int depth) {
        RenderableObject ret = new RenderableObject();

        Set<String> keys = json.keySet();

        for (String it : keys) {
            Object entry = json.get(it);
            int newDepth = (depth < 6) ? depth + 1 : depth;
            String objectTitle = "<h" + newDepth + ">" + it + "</h" + newDepth + ">";

            if (entry instanceof JSONObject) {
                RenderableObject o = toRenderableObject((JSONObject)entry, newDepth);
                o.setObjectTitle(objectTitle);

                ret.addObject(o);
            } else
            if (entry instanceof JSONArray) {
                RenderableObject o = toRenderableObject(it, (JSONArray)entry, newDepth);
                o.setObjectTitle(objectTitle);

                ret.addObject(o);
            } else {
                ret.addAttribute(new AttributeList(it, entry.toString()));
            }
        }
        

        return ret;
    }

    private static RenderableObject toRenderableObject(String key, JSONArray jsonArray, int depth) {
        RenderableObject ret = new RenderableObject();

        for (int i = 0; i < jsonArray.length(); i++) {
            Object entry = jsonArray.get(i);
            int newDepth = (depth < 6) ? depth + 1 : depth;

            if (entry instanceof JSONObject) {
                RenderableObject o = toRenderableObject((JSONObject)entry, newDepth);

                ret.addObject(o);
            } else
            if (entry instanceof JSONArray) {
                RenderableObject o = toRenderableObject("", (JSONArray)entry, newDepth);
                ret.addObject(o);
            } else {
                ret.addAttribute(new AttributeList(" - ", entry.toString()));
            }
        }

        return ret;
    }

}
