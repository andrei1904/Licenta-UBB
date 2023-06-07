package model;

public class Request {

    private int requestCode;
    private int parkId;

    public Request(int requestCode, int parkId) {
        this.requestCode = requestCode;
        this.parkId = parkId;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }
}
