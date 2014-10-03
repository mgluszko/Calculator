package DataTypes;
import java.util.ArrayList;
import java.util.Collections;


public class Char{
	public ArrayList<AbilityArray> abilities;
	public double inheritAP;
	public double inheritMpen;
	public double inheritPmpen;
	public double inheritCDR;
	public ArrayList<Bonus> bonuses;
	
	public Char(double inheritAp, double inheritMpen, double inheritPmpen, double inheritCDR){
		this.inheritAP = inheritAp;
		this.inheritMpen = inheritMpen;
		this.inheritPmpen = inheritPmpen;
		this.inheritCDR = inheritCDR;
		abilities = new ArrayList<>();
		bonuses = new ArrayList<>();
	}
	
	public boolean moveAbilityUp(int index){
		if(index > 1){
			Collections.swap(abilities, index, index-1);
			return true;
		}
		return false;
	}
	
	public boolean moveAbilityDown(int index){
		if(index < abilities.size() -1){
			Collections.swap(abilities, index, index+1);
			return true;
		}
		return false;
	}
	
}
