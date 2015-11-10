package com.samsung.service.util;

import java.util.HashMap;
import java.util.Vector;

public class DataTable
{

    private String strName;
    private Vector vt;

    public DataTable()
    {
        vt = new Vector();
    }

    public DataTable(String strTableName)
    {
        vt = new Vector();
        strName = strTableName.toUpperCase();
    }

    public void setTableName(String strTableName)
    {
        strName = strTableName;
    }

    public void addRow(HashMap hm)
    {
        vt.add(hm);
    }

    public String getValue(int i, String strCol)
    {
        if(i >= vt.size())
        {
            return "";
        }
        try
        {
            HashMap hm = (HashMap)vt.get(i);
            return hm.get(strCol.toUpperCase()).toString().trim();
        }
        catch(Exception exception)
        {
            return "";
        }
    }

    public boolean setValue(int i, String strCol, String strValue) // NO_UCD (unused code)
    {
        if(i >= vt.size())
        {
            return false;
        }
        try
        {
            HashMap hm = (HashMap)vt.get(i);
            hm.put(strCol.toUpperCase(), strValue);
        }
        catch(Exception ex)
        {
            return false;
        }
        return true;
    }

    public boolean delRow(int i) // NO_UCD (unused code)
    {
        if(i >= vt.size())
        {
            return false;
        }
        try
        {
            vt.remove(i);
        }
        catch(Exception ex)
        {
            return false;
        }
        return true;
    }

    public DataTable getTable(int i, String strCol) // NO_UCD (unused code)
    {
        if(i >= vt.size())
        {
            return new DataTable();
        }
        try
        {
            HashMap hm = (HashMap)vt.get(i);
            return (DataTable)hm.get(strCol.toUpperCase());
        }
        catch(Exception exception)
        {
            return new DataTable();
        }
    }

    public int getLength()
    {
        try
        {
            return vt.size();
        }
        catch(Exception exception)
        {
            return 0;
        }
    }
}
