package net.sf.memoranda;

public interface Timeable extends Lockable {
	public long getActualEffort();
	public void setActualEffort(long newActualEffort);
	public void addActualEffort(long effortToAdd);
}
