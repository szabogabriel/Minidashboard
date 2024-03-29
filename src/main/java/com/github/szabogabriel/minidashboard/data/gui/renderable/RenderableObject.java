package com.github.szabogabriel.minidashboard.data.gui.renderable;

import java.util.ArrayList;
import java.util.List;

public class RenderableObject {
    private List<AttributeList> attributeList = new ArrayList<>();
    private List<RenderableObject> objectList = new ArrayList<>();
    private String objectTitle = "";
    private String content = "";

    public void setObjectTitle(String title) {
        this.objectTitle = title;
    }

    public void addAttribute(AttributeList al) {
        attributeList.add(al);
    }

    public void addObject(RenderableObject d) {
        objectList.add(d);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AttributeList> getAttributeList() {
        return attributeList;
    }

    public List<AttributeList> attributeList() {
        return attributeList;
    }

    public List<RenderableObject> getObjectList() {
        return objectList;
    }

    public List<RenderableObject> objectList() {
        return objectList;
    }

    public String getObjectTitle() {
        return objectTitle;
    }

    public String objectTitle() {
        return objectTitle;
    }

    public String getContent() {
        return content;
    }

    public String content() {
        return content;
    }

}
