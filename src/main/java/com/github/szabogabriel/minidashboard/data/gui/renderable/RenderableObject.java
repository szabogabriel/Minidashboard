package com.github.szabogabriel.minidashboard.data.gui.renderable;

import java.util.ArrayList;
import java.util.List;

public class RenderableObject {
    private List<AttributeList> attributeList = new ArrayList<>();
    private List<RenderableObject> objectList = new ArrayList<>();
    private String objectTitle;

    public void setObjectTitle(String title) {
        this.objectTitle = title;
    }

    public void addAttribute(AttributeList al) {
        attributeList.add(al);
    }

    public void addObject(RenderableObject d) {
        objectList.add(d);
    }

    public List<AttributeList> getAttributeList() {
        return attributeList;
    }

    public List<RenderableObject> getObjectList() {
        return objectList;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

}
