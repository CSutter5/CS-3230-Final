public class PCB {
    private static int pidCounter = 1;
    private int pid;
    private String name;
    private State state;
    private boolean active;

    public enum State {
        READY, RUNNING, BLOCKED
    }

    public PCB(String name) {
        this.pid = pidCounter++;
        this.name = name;
        this.state = State.READY;
        this.active = true;
    }

    public int getPid() { return pid; }
    public String getName() { return name; }
    public State getState() { return state; }
    public void setState(State state) { this.state = state; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
