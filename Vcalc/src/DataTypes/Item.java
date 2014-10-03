package DataTypes;
import java.util.ArrayList;


public class Item{
	public final double ap;
	public final double mpen;
	public final double Pmpen;
	public final double cdr;
	
	private ArrayList<Bonus> bonuses;
	
	public Item(double aap, double ampen, double ampenp, double acdr){
		ap = aap;
		mpen = ampen;
		Pmpen = ampenp;
		cdr = acdr;
		bonuses = new ArrayList<Bonus>();
	}
	
	public void addBonus(Bonus bonus){
		bonuses.add(bonus);
	}
	
	public ArrayList<Bonus> getBonuses(){
		return new ArrayList<>(bonuses);
	}

}
