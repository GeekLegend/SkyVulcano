package fr.geeklegend.vylaria.skyvulcano.game;

public enum GameState
{
	
	WAITING, GAME, FINISH;
	
	private static GameState current;

	public static GameState getState()
	{
		return current;
	}
	
	public static boolean isState(GameState state)
	{
		return current == state;
	}
	
	public static void setState(GameState state)
	{
		current = state;
	}
	
}
