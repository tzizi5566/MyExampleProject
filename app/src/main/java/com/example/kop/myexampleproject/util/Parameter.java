package com.example.kop.myexampleproject.util;

import java.io.Serializable;

public class Parameter implements Serializable, Comparable<Parameter> {

    private String name;

    private String value;

    public Parameter() {
        super();
    }

    public Parameter(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Parameter[name=" + name + ",value=" + value + "]";
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Parameter) {
            Parameter param = (Parameter) obj;
            return this.name.equals(param.getName()) && this.getValue().equals(param.getValue());
        }
        return false;
    }

    public int compareTo(Parameter o) {
        int compared = 0;
        compared = name.compareTo(o.getName());
        if (compared == 0) {
            compared = value.compareTo(o.getValue());
        }
        return compared;
    }
}
