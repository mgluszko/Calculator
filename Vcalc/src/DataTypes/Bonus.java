package DataTypes;


public class Bonus{
	public final BonusType type;
	public final int priority;
	public final double value;
	
	public Bonus(BonusType type, double value, int priority){
		this.type= type;
		this.value = value;
		this.priority = priority;
	}

}
