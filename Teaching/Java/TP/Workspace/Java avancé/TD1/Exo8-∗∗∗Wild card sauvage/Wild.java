public abstract class Wild <T> {
	public abstract void sup(Wild<?  super  T> w);
	public abstract void ext(Wild<? extends T> w);
}
