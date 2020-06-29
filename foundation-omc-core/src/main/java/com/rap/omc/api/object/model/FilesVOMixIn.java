package com.rap.omc.api.object.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class FilesVOMixIn {
    @JsonIgnore
    abstract public void setCheckouted(String checkouted);
    @JsonIgnore
    abstract public void setModified(String modified);
    @JsonIgnore
    abstract public void setCreated(String created);
}
