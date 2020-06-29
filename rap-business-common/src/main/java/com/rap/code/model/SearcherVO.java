package com.rap.code.model;

public class SearcherVO {

    private String className;

    private String name;

    private String title;

    private String obid;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Override
    public String toString(){
        return "SearcherVO [ name=" + name + "title=" + title + "]";
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 
     * 
     * @return the obid
     */
    public String getObid(){
        return obid;
    }

    /**
     * 
     * 
     * @param obid the obid to set
     */
    public void setObid(String obid){
        this.obid = obid;
    }
}
