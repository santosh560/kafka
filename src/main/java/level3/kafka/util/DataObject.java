package level3.kafka.util;

import java.io.Serializable;

public class DataObject implements Serializable {
    private String objValue1 = null;
    private String objValue2 = null;

    public DataObject(String s1, String s2) {
        this.objValue1 = s1;
        this.objValue2 = s2;
    }

    @Override
    public String toString() {
        return "DataObject { objValue1='" + objValue1 + "\', objValue2='" + objValue2 + "\'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataObject that = (DataObject) o;

        if ((this.objValue1 != null) && (!this.objValue1.equals(that.objValue1))) return false;
        return ((this.objValue2 != null) && (this.objValue2.equals(that.objValue2)));
    }

    public String getObjValue1() {
        return objValue1;
    }

    public void setObjValue1(String objValue1) {
        this.objValue1 = objValue1;
    }

    public String getObjValue2() {
        return objValue2;
    }

    public void setObjValue2(String objValue2) {
        this.objValue2 = objValue2;
    }

    @Override
    public int hashCode() {
        int result = objValue1.hashCode();
        result = 31 * result + objValue2.hashCode();
        return result;
    }
}

