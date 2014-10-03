package DataTypes;
import java.util.ArrayList;

public class AbilityArray{
	public static Ability nullAbility = new Ability().dmg(0).cd(0).ratio(0);
	private ArrayList<Ability> levels;
	private int currentLevel = 0;
	private final String name;

	public AbilityArray(String name){
		this.name = name;
		levels = new ArrayList<>();
		levels.add(nullAbility);
	}

	public AbilityArray addLevel(Ability ability){
		levels.add(ability);
		return this;
	}

	public int setCurrentLevel(int level){
		if(level > 0 && level < levels.size()){
			currentLevel = level;
		}
		return currentLevel;
	}

	public boolean removeLevel(){
		if(levels.size() > 1){
			levels.remove(levels.size() - 1);
			if(currentLevel >= levels.size()){
				currentLevel = 0;
			}
			return true;
		}
		return false;
	}

	public void removeAll(){
		levels.clear();
		currentLevel = 0;
		levels.add(nullAbility);
	}

	public int getMaxLevel(){
		return levels.size() - 1;
	}

	public Ability getLevel(int level){
		return (level > 0 && level < levels.size()) ? levels.get(level) : levels.get(0);
	}

	public Ability getActiveLevel(){
		return levels.get(currentLevel);
	}

	public int getCurrentLevel(){
		return currentLevel;
	}

}
