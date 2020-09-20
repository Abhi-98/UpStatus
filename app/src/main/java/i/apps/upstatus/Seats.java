package i.apps.upstatus;

public class Seats {

    String clss,code,avail;

    public Seats(String clss, String code, String avail) {
        this.clss = clss;
        this.code = code;
        this.avail = avail;
    }

    public Seats() {
    }

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }
}
