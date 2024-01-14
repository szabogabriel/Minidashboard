package com.github.szabogabriel.minidashboard.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import com.github.szabogabriel.minidashboard.data.gui.renderable.RenderableObject;

public class JsonToObjectRendererUtilTest {

    private RenderableObject prepare(String json) {
        JSONObject object = new JSONObject(json);

        RenderableObject result = JsonToObjecRendererUtil.toRenderableObject(object);
        return result;
    }

    @Test
    public void testEmptyObject() {
        RenderableObject result = prepare("{}");

        assertNotNull(result);
    }

    @Test
    public void testSingleStringElement() {
        RenderableObject result = prepare("{ \"key\": \"value\" }");

        assertNotNull(result);
        assertEquals(1, result.getAttributeList().size());
        assertEquals("key", result.getAttributeList().get(0).getKey());
        assertEquals("value", result.getAttributeList().get(0).getValue());
    }

    @Test
    public void testSingleNumberElement() {
        RenderableObject result = prepare("{ \"key\": 123 }");

        assertNotNull(result);
        assertEquals(1, result.getAttributeList().size());
        assertEquals("key", result.getAttributeList().get(0).getKey());
        assertEquals("123", result.getAttributeList().get(0).getValue());
    }

    @Test
    public void testEmbeddedObject() {
        RenderableObject result = prepare("{ \"key1\": { \"key2\" : \"value2\" }}");

        assertNotNull(result);
        assertEquals(0, result.getAttributeList().size());
        assertEquals(1, result.getObjectList().size());
        assertEquals("key2", result.getObjectList().get(0).getAttributeList().get(0).getKey());
        assertEquals("value2", result.getObjectList().get(0).getAttributeList().get(0).getValue());
        assertEquals("<h2>key1</h2>", result.getObjectList().get(0).getObjectTitle());
    }

    @Test
    public void testArray() {
        RenderableObject result = prepare("{ \"key\" : [\"value1\", \"value2\", \"value3\"]}");

        assertNotNull(result);
        assertEquals(0, result.getAttributeList().size());
        assertEquals(1, result.getObjectList().size());
        assertEquals("<h2>key</h2>", result.getObjectList().get(0).getObjectTitle());
    }
    
}
