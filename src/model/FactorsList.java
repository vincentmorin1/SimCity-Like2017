package model;

import model.Factor;

public class FactorsList extends Factor {

	private Factor cityAttractivity = new Factor();
	private Factor diseases = new Factor();
	private Factor economy = new Factor();
	private Factor education = new Factor();
	private Factor efficiencyAtWork = new Factor();
	private Factor employment = new Factor();
	private Factor energy = new Factor();
	private Factor entertainment = new Factor();
	private Factor happiness = new Factor();
	private Factor health = new Factor();
	private Factor housing = new Factor();
	private Factor industry = new Factor();
	private Factor lifeConditions = new Factor();
	private Factor lifeNeeds = new Factor();
	private Factor order = new Factor();
	private Factor pollution = new Factor();
	private Factor progress = new Factor();
	private Factor realEstate = new Factor();
	private Factor rents = new Factor();
	private Factor revenues = new Factor();
	private Factor salaries = new Factor();
	private Factor safety = new Factor();
	private Factor spendings = new Factor();
	private Factor taxes = new Factor();
	private Factor trade = new Factor();
	private Factor workConditions = new Factor();
	private Factor workTime = new Factor();
	
	public FactorsList() {
	}
	
	public int getHappiness() { 
		return this.happiness.getValue();
	}
	
	public int getEfficiencyAtWork() {
		return this.efficiencyAtWork.getValue();
	}
	
	public int getEconomy() {
		return this.economy.getValue();
	}
	
	public void update() {
		final int D_diseases = this.diseases.getEvolution();
		final int D_economy = this.economy.getEvolution();
		final int D_education = this.education.getEvolution();
		final int D_efficiencyAtWork = this.efficiencyAtWork.getEvolution();
		final int D_employment = this.employment.getEvolution();
		final int D_energy = this.energy.getEvolution();
		final int D_entertainment = this.entertainment.getEvolution();
		final int D_happiness = this.happiness.getEvolution();
		final int D_health = this.health.getEvolution();
		final int D_housing = this.housing.getEvolution();
		final int D_industry = this.industry.getEvolution();
		final int D_lifeConditions = this.lifeConditions.getEvolution();
		final int D_lifeNeeds = this.lifeNeeds.getEvolution();
		final int D_order = this.order.getEvolution();
		final int D_pollution = this.pollution.getEvolution();
		final int D_progress = this.progress.getEvolution();
		final int D_realEstate = this.realEstate.getEvolution();
		final int D_rents = this.rents.getEvolution();
		final int D_revenues = this.revenues.getEvolution();
		final int D_salaries = this.salaries.getEvolution();
		final int D_safety = this.safety.getEvolution();
		final int D_spendings = this.spendings.getEvolution();
		final int D_taxes = this.taxes.getEvolution();
		final int D_trade = this.trade.getEvolution();
		final int D_workConditions = this.workConditions.getEvolution();
		final int D_workTime = this.workTime.getEvolution();
		
		this.cityAttractivity.evolve(D_progress);
		this.progress.evolve(1/3*(D_economy+D_efficiencyAtWork+D_happiness));
		this.economy.evolve(1/2*(D_revenues-D_spendings));
		this.revenues.evolve(1/2*(D_taxes+D_rents));
		this.taxes.evolve(D_employment);
		this.employment.evolve(1/7*(D_education+D_energy+D_health+D_industry+D_order+D_realEstate+D_trade));
		this.education.evolve(0/*school*/);
		this.energy.evolve(0/*power*/);
		this.health.evolve(0/*hospital*/);
		this.industry.evolve(0/*industrial*/);
		this.order.evolve(0/*police*/);
		this.realEstate.evolve(0/*Residential*/);
		this.trade.evolve(0/*commercial*/);
		this.rents.evolve(D_housing);
		this.spendings.evolve(D_salaries);
		this.salaries.evolve(D_workConditions);
		this.workConditions.evolve(1/2*(D_employment-D_workTime));
		this.workTime.evolve(-D_efficiencyAtWork);
		this.efficiencyAtWork.evolve(1/3*(D_economy+D_education-D_entertainment));
		this.entertainment.evolve(0/*Ski Plages Commercial*/);
		this.happiness.evolve(1/3*(D_entertainment+D_lifeConditions+D_workConditions));
		this.lifeConditions.evolve(1/4*(-D_diseases+D_housing+D_lifeNeeds+D_safety));
		this.diseases.evolve(1/3*(-D_health-D_housing+D_pollution));
		this.housing.evolve(D_realEstate);
		this.pollution.evolve(D_industry);
		this.lifeNeeds.evolve(1/3*(D_industry+D_salaries+D_trade));
		this.safety.evolve(1/2*(D_economy+D_order));
	}
}
