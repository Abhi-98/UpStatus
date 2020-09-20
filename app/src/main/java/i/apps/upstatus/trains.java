package i.apps.upstatus;

public class trains {

    String stn,code,dis,dep,arr;

    public trains(String stn, String code, String dis, String dep, String arr) {
        this.stn = stn;
        this.code = code;
        this.dis = dis;
        this.dep = dep;
        this.arr = arr;
    }

    public trains() {
    }

    public String getStn() {
        return stn;
    }

    public void setStn(String stn) {
        this.stn = stn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getArr() {
        return arr;
    }

    public void setArr(String arr) {
        this.arr = arr;
    }
}
