package gui;

import org.omg.CORBA.OBJ_ADAPTER;

import javax.management.Descriptor;
import javax.management.RuntimeOperationsException;
import java.util.HashMap;
import java.util.Map;

public class MyDescriptor implements Descriptor {

    HashMap<String, Object> values = new HashMap<>();

    @Override
    public Object getFieldValue(String fieldName) throws RuntimeOperationsException {
        return values.get(fieldName);
    }

    @Override
    public void setField(String fieldName, Object fieldValue) throws RuntimeOperationsException {
        if (!values.containsKey(fieldName))
            values.put(fieldName, fieldValue);
        else
            values.replace(fieldName, fieldValue);
    }

    @Override
    public String[] getFields() {
        System.out.println(1);
        return new String[0];
    }

    @Override
    public String[] getFieldNames() {

        System.out.println(2);
        return new String[0];
    }

    @Override
    public Object[] getFieldValues(String... fieldNames) {

        System.out.println(3);
        return new Object[0];
    }

    @Override
    public void removeField(String fieldName) {

        System.out.println(4);
    }

    @Override
    public void setFields(String[] fieldNames, Object[] fieldValues) throws RuntimeOperationsException {

        System.out.println(5);
    }

    @Override
    public Object clone() throws RuntimeOperationsException {
        System.out.println(6);
        return null;
    }

    @Override
    public boolean isValid() throws RuntimeOperationsException {

        System.out.println(7);
        return false;
    }
}
