package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import DataTypes.Ability;
import DataTypes.AbilityArray;
import DataTypes.Bonus;
import DataTypes.Char;
import DataTypes.Item;

public class Calculator{
	private final static int itemLimit = 6;
	private int enemyMr;
	private Char character = null;
	private HashMap<String, Item> items = null;
	private ArrayList<Item> itemsEquipped;
	private double time;
	private double ap;
	private double cdr;
	private double mpen;
	private double pmpen;
	private double apMultiplier;
	private double dmgMultiplier;
	private double totalAP;
	private double totalDamage;
	private double resist;

	public Calculator(int enemyMr, HashMap<String, Item> items, Char character, double time){
		this.enemyMr = enemyMr;
		this.items = items;
		this.character = character;
		this.time = time;
		this.itemsEquipped = new ArrayList<>();

		update();
	}

	public void addCharAbility(AbilityArray ability){
		character.abilities.add(ability);
	}

	public boolean removeCharAbility(Integer index){
		return character.abilities.remove(index);
	}

	public boolean addItem(String name, Item item){
		return items.put(name, item) == null ? true : false;
	}

	public boolean removeItem(String name){
		if(items.get(name) != null){
			while(unequipItem(name))
				;
			items.remove(name);
			return true;
		}
		return false;
	}

	public boolean equipItem(String name){
		if(items.get(name) != null){
			if(itemsEquipped.size() <= itemLimit){
				return itemsEquipped.add(items.get(name));
			}
		}
		return false;
	}

	public boolean unequipItem(String name){
		return itemsEquipped.remove(items.get(name));
	}

	public double update(){
		ap = character.inheritAP;
		cdr = character.inheritCDR;
		mpen = character.inheritMpen;
		pmpen = 0;
		apMultiplier = 1;
		dmgMultiplier = 1;

		ArrayList<Double> pmpens = new ArrayList<>();
		HashMap<Integer, ArrayList<Bonus>> bonuses = new HashMap<>();

		pmpens.add(character.inheritPmpen);
		addBonuses(bonuses, character.bonuses);

		for(Item item : itemsEquipped){
			ap += item.ap;
			cdr = (cdr + item.cdr) > 40 ? 40 : (cdr + item.cdr);
			mpen += item.mpen;
			if(item.Pmpen > 0){
				pmpens.add(item.Pmpen);
			}
			addBonuses(bonuses, item.getBonuses());
		}
		pmpen = calcMultiplicativeStack(pmpens);

		ArrayList<Integer> priorities = new ArrayList<>(bonuses.keySet());
		Collections.sort(priorities);
		for(Integer i : priorities){
			for(Bonus bonus : bonuses.get(i)){
				switch(bonus.type){
				case AP:
					apMultiplier *= (1d + (bonus.value / 100d));
					break;
				case DMG:
					dmgMultiplier *= (1d + (bonus.value / 100d));
					break;
				default:
					break;
				}
			}
		}

		totalAP = ap * apMultiplier;
		totalDamage = calcAbilitiesDamage(character, cdr, totalAP, time);
		resist = calcMR(pmpen, mpen, enemyMr);
		totalDamage = totalDamage * dmgMultiplier;
		totalDamage = (100d / (100d + resist)) * totalDamage;

		return totalDamage;
	}

	private void addBonuses(HashMap<Integer, ArrayList<Bonus>> bonuses, ArrayList<Bonus> incoming){
		if(bonuses == null){
			return;
		}

		for(Bonus bonus : incoming){
			Integer priority = bonus.priority;

			if(bonuses.get(priority) == null){
				bonuses.put(priority, new ArrayList<Bonus>());
			}
			bonuses.get(priority).add(bonus);
		}
	}

	private double calcMR(double pmpen, double mpen, double mr){
		return((mr - mpen) * (1d - pmpen));
	}

	private double calcMultiplicativeStack(List<Double> values){
		double cumProduct = 1;
		for(double value : values){
			cumProduct *= (1d - (value / 100d));
		}

		return 1d - cumProduct;
	}

	private double calcAbilitiesDamage(Char character, double totalCdr, double totalAP, double time){

		ArrayList<Double> atomicDamages = new ArrayList<>();
		ArrayList<Double> totalDamages = new ArrayList<>();

		for(AbilityArray ability : character.abilities){
			Ability current = ability.getActiveLevel();
			double dmg = current.dmg + (current.ratio * totalAP);
			atomicDamages.add(dmg);
			int times = 1 + ((int)(time / (current.cd * (1 - (totalCdr / 100d)))));
			totalDamages.add(dmg * times);
		}

		double total = 0;
		for(Double dmg : totalDamages){
			total += dmg;
		}
		return total;
	}

	public int getEnemyMr(){
		return enemyMr;
	}

	public Char getCharacter(){
		return character;
	}

	public HashMap<String, Item> getItems(){
		return items;
	}

	public ArrayList<Item> getItemsEquipped(){
		return itemsEquipped;
	}

	public double getTime(){
		return time;
	}

	public double getAp(){
		return ap;
	}

	public double getCdr(){
		return cdr;
	}

	public double getMpen(){
		return mpen;
	}

	public double getPmpen(){
		return pmpen;
	}

	public double getApMultiplier(){
		return apMultiplier;
	}

	public double getDmgMultiplier(){
		return dmgMultiplier;
	}

	public double getTotalAP(){
		return totalAP;
	}

	public double getTotalDamage(){
		return totalDamage;
	}

	public double getResist(){
		return resist;
	}
}
