package com.liwy.anything.view.gridview;

/**
 * Created by liwy on 16/7/14.
 */
public class GridItem {
    /**
     * item's name
     */
    private String name;
    /**
     * item image's src id
     */
    private int id;//图片id
    /**ß
     * the destination of clicked of the item
     */
    private Class dstClass;

    public GridItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getDstClass() {
        return dstClass;
    }

    public void setDstClass(Class dstClass) {
        this.dstClass = dstClass;
    }
}
