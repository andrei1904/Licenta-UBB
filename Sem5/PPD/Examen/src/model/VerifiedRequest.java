package model;

public class VerifiedRequest {

    private int requestCode;
    private int parkId;
    private int functionarId;

    public VerifiedRequest(int requestCode, int parkId, int functionarId) {
        this.requestCode = requestCode;
        this.parkId = parkId;
        this.functionarId = functionarId;
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

    public int getFunctionarId() {
        return functionarId;
    }

    public void setFunctionarId(int functionarId) {
        this.functionarId = functionarId;
    }
}
