package model;

import model.Factor;
import model.CityResources;
import model.tiles.BeachTile;
import model.tiles.CommercialTile;
import model.tiles.HospitalTile;
import model.tiles.PoliceOfficeTile;
import model.tiles.ResidentialTile;
import model.tiles.SchoolTile;
import model.tiles.SnowStationTile;

public class FactorsList extends Factor {

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
	
	public int calculValue(int n, int d) {
		if (d == 0 || n >= 2*d)
			return 100;
		return 50 * n/d;
	}
	
	public void update(CityResources res) {
		
		// Stats values
		final int nbBuildingsNeedingEnergy = res.getNbSchools()+res.getNbHospitals()+res.getNbIndustrials()+res.getNbPoliceOffices()+res.getNbResidentials()+res.getNbCommercials()+res.getNbSnowStations()+res.getNbPowerPlants();
		final int nbBuildingsNeedingIndustry = res.getNbSchools()+res.getNbHospitals()+res.getNbPoliceOffices()+res.getNbResidentials()+res.getNbCommercials()+res.getNbSnowStations()+res.getNbPowerPlants();
		
		int V_schools = calculValue(res.getNbSchools()*SchoolTile.DEFAULT_NUMBER_STUDENT_MAX, res.getStudentPopulation());
		int V_powerPlants = calculValue(res.getNbPowerPlants()*4, nbBuildingsNeedingEnergy);
		int V_hospitals = calculValue(res.getNbHospitals()*HospitalTile.DEFAULT_NUMBER_INHABITANTS_CARED, res.getPopulation());
		int V_industrials = calculValue(res.getNbIndustrials()*6, nbBuildingsNeedingIndustry);
		int V_policeOffices = calculValue(res.getNbPoliceOffices()*PoliceOfficeTile.DEFAULT_NUMBER_INHABITANTS_CARED, res.getPopulation());
		int V_residentials = calculValue(res.getNbResidentials()*ResidentialTile.DEFAULT_INHABITANTS_CAPACITY, res.getPopulation());
		int V_commercials = calculValue(res.getNbCommercials()*CommercialTile.DEFAULT_CONSUMPTION_CAPACITY, res.getPopulation());
		int V_snowStations = calculValue(res.getNbSnowStations()*SnowStationTile.DEFAULT_NUMBER_TOURISTS_MAX, res.getPopulation());
		int V_beaches = calculValue(res.getNbBeaches()*BeachTile.DEFAULT_NUMBER_TOURISTS_MAX, res.getPopulation());
		//System.out.println("");

		
		final int D_diseases = this.diseases.getValue();
		final int D_economy = this.economy.getValue();
		final int D_education = this.education.getValue();
		final int D_efficiencyAtWork = this.efficiencyAtWork.getValue();
		final int D_employment = this.employment.getValue();
		final int D_energy = this.energy.getValue();
		final int D_entertainment = this.entertainment.getValue();
		final int D_happiness = this.happiness.getValue();
		final int D_health = this.health.getValue();
		final int D_housing = this.housing.getValue();
		final int D_industry = this.industry.getValue();
		final int D_lifeConditions = this.lifeConditions.getValue();
		final int D_lifeNeeds = this.lifeNeeds.getValue();
		final int D_order = this.order.getValue();
		final int D_pollution = this.pollution.getValue();
		final int D_realEstate = this.realEstate.getValue();
		final int D_rents = this.rents.getValue();
		final int D_revenues = this.revenues.getValue();
		final int D_salaries = this.salaries.getValue();
		final int D_safety = this.safety.getValue();
		final int D_spendings = this.spendings.getValue();
		final int D_taxes = this.taxes.getValue();
		final int D_trade = this.trade.getValue();
		final int D_workConditions = this.workConditions.getValue();
		final int D_workTime = this.workTime.getValue();
		
		
		// Factors evolutions
		this.education.setValue(V_schools);
		this.energy.setValue(V_powerPlants);
		this.health.setValue(V_hospitals);
		this.industry.setValue(V_industrials);
		this.order.setValue(V_policeOffices);
		this.realEstate.setValue(V_residentials);
		this.trade.setValue(V_commercials);
		this.entertainment.setValue((V_snowStations+V_beaches+V_commercials)/3);
		
		this.progress.setValue((D_economy+D_efficiencyAtWork+D_happiness)/3);
		this.economy.setValue((D_revenues+100-D_spendings)/2);
		this.revenues.setValue((D_taxes+D_rents)/2);
		this.taxes.setValue(D_employment);
		this.employment.setValue((D_education+D_energy+D_health+D_industry+D_order+D_realEstate+D_trade)/7);
		this.rents.setValue(D_housing);
		this.spendings.setValue(D_salaries);
		this.salaries.setValue(D_workConditions);
		this.workConditions.setValue((D_employment+100-D_workTime)/2);
		this.workTime.setValue(100-D_efficiencyAtWork);
		this.efficiencyAtWork.setValue((D_economy+D_education+100-D_entertainment)/3);
		this.happiness.setValue((D_entertainment+D_lifeConditions+D_workConditions)/3);
		this.lifeConditions.setValue((100-D_diseases+D_housing+D_lifeNeeds+D_safety)/4);
		this.diseases.setValue((100-D_health+100-D_housing+D_pollution)/3);
		this.housing.setValue(D_realEstate);
		this.pollution.setValue(D_industry);
		this.lifeNeeds.setValue((D_industry+D_salaries+D_trade)/3);
		this.safety.setValue((D_economy+D_order)/2);
			
	}
}
