public class Fork extends Thread {
    private Boolean isFree;

    public Fork(Boolean isFree) {
        this.isFree = isFree;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

}
