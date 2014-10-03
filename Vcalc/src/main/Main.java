package main;
import java.util.HashMap;

import model.Calculator;
import DataTypes.Ability;
import DataTypes.AbilityArray;
import DataTypes.Bonus;
import DataTypes.BonusType;
import DataTypes.Char;
import DataTypes.Item;

public class Main{

	static HashMap<String, Item> items;
	static Char character;

	static double time = 1;
	static double inheritAP = 28;
	static double inheritMpen = 9;
	static double inheritPmpen = 6;
	static double inheritCDR = 5;
	static double bonusAP = 5;
	static double bonusDMG = 3;
	static int bonusDmgPriority = 20;
	static int bonusAPPriority = 10;
	static int enemyMr = 70;

	public static void main(String[] args){
		initItems();
		initChar();

		Calculator calc = new Calculator(enemyMr, items, character, time);
		//calc.equipItem("sorcs");
		//calc.equipItem("codex");
		//calc.equipItem("rod");
		//calc.equipItem("zhonya");
		//calc.equipItem("dfg");
		//calc.equipItem("roa");
		//calc.equipItem("void");
		//calc.equipItem("rabadons");
		calc.update();

		System.out.println(calc.getTotalAP());
		System.out.println((int)(calc.getTotalDamage()));
	}

	public static void initItems(){
		items = new HashMap<>();

		Item vstaff = new Item(70, 0, 35, 0);
		Item dfg = new Item(120, 0, 0, 10);
		dfg.addBonus(new Bonus(BonusType.DMG, 20, bonusDmgPriority));
		Item rabadons = new Item(120, 0, 0, 0);
		rabadons.addBonus(new Bonus(BonusType.AP, 30, bonusAPPriority));
		Item roa = new Item(80, 0, 0, 0);
		Item sorcs = new Item(0, 15, 0, 0);
		Item codex = new Item(30, 0, 0, 10);
		Item zhonya = new Item(120, 0, 0, 0);
		Item rod = new Item(80,0,0,0);

		items.put("void", vstaff);
		items.put("dfg", dfg);
		items.put("rabadons", rabadons);
		items.put("roa", roa);
		items.put("sorcs", sorcs);
		items.put("codex", codex);
		items.put("zhonya", zhonya);
		items.put("rod", rod);
	}

	public static void initChar(){
		AbilityArray ability1 = new AbilityArray("Q");
		ability1.addLevel(new Ability().dmg(80).ratio(0.6).cd(8)).addLevel(new Ability().dmg(125).ratio(0.6).cd(7));
		ability1.addLevel(new Ability().dmg(170).ratio(0.6).cd(6)).addLevel(new Ability().dmg(215).ratio(0.6).cd(5));
		ability1.addLevel(new Ability().dmg(260).ratio(0.6).cd(4));

		AbilityArray ability2 = new AbilityArray("W");
		ability2.addLevel(new Ability().dmg(120).ratio(1).cd(10));
		ability2.addLevel(new Ability().dmg(170).ratio(1).cd(10));
		ability2.addLevel(new Ability().dmg(220).ratio(1).cd(10));
		ability2.addLevel(new Ability().dmg(270).ratio(1).cd(10));
		ability2.addLevel(new Ability().dmg(320).ratio(1).cd(10));

		AbilityArray ability3 = new AbilityArray("E");
		
		AbilityArray ability4 = new AbilityArray("R");
		ability4.addLevel(new Ability().dmg(250).ratio(1.2).cd(130));
		ability4.addLevel(new Ability().dmg(375).ratio(1.2).cd(110));
		ability4.addLevel(new Ability().dmg(500).ratio(1.2).cd(90));

		character = new Char(inheritAP, inheritMpen, inheritPmpen, inheritCDR);
		character.abilities.add(ability1);
		character.abilities.add(ability2);
		character.abilities.add(ability3);
		character.abilities.add(ability4);
		
		character.abilities.get(0).setCurrentLevel(4);
		character.abilities.get(1).setCurrentLevel(2);
		character.abilities.get(3).setCurrentLevel(1);
		character.bonuses.add(new Bonus(BonusType.DMG, bonusDMG, bonusDmgPriority));
		character.bonuses.add(new Bonus(BonusType.AP, bonusAP, bonusAPPriority));

	}

}
