package com.rap.omc.framework.exception;

import java.awt.DisplayMode;
public class ExceptionOptionalInfo
{

    private String messageAddContent;

    private DisplayMode displayMode;


    public ExceptionOptionalInfo()
    {

    }
    public ExceptionOptionalInfo(String messageAddContent)
    {
        this.messageAddContent = messageAddContent;
        this.displayMode = null;
    }


    public ExceptionOptionalInfo(DisplayMode displayMode)
    {
        this.messageAddContent = null;
        this.displayMode = displayMode;
    }


    public ExceptionOptionalInfo(String messageAddContent, DisplayMode displayMode)
    {
        this.messageAddContent = messageAddContent;
        this.displayMode = displayMode;
    }


    public String getMessageAddContent()
    {
        return this.messageAddContent;
    }


    public void setMessageAddContent(String addContent)
    {
        this.messageAddContent = addContent;
    }


    public void setDisplayMode(DisplayMode displayMode)
    {
        this.displayMode = displayMode;
    }


    public DisplayMode getDisplayMode()
    {
        return this.displayMode;
    }
}