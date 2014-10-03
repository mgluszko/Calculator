package DataTypes;

public class Ability{
	public int dmg;
	public double ratio;
	public double cd;
	
	public Ability dmg(int dmg){
		this.dmg = dmg;
		return this;
	}
	
	public Ability ratio(double ratio){
		this.ratio = ratio;
		return this;
	}
	
	public Ability cd(double cd){
		this.cd = cd;
		return this;
	}
	
}
