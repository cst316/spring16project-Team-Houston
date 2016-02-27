package net.sf.memoranda;

public interface Lockable {
    void lock();
    void unlock();
    boolean isLocked();
}
