	package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.CommercialTile;

public class CommercialTileTest {
    
    @Test
    public void testInit() {
        CommercialTile ct = new CommercialTile();
        Assert.assertEquals(CommercialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION, ct.getEvolutionEnergyConsumption());
        Assert.assertEquals(CommercialTile.DEFAULT_MAX_NEEDED_ENERGY, ct.getMaxNeededEnergy());
        Assert.assertEquals(CommercialTile.DEFAULT_WORKERS_CAPACITY, ct.getInhabitantsCapacity());
        ct = new CommercialTile(10,0,0);
        Assert.assertEquals(10, ct.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
    	CommercialTile ct = new CommercialTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getMoneyProduction();
        ct.update(resources);
        Assert.assertEquals(initialValue + ct.getProduction(), resources.getEnergyProduction());
        
    }
    
    @Test
    public void testDisassemble() {
    	CommercialTile ct = new CommercialTile();
        CityResources resources = new CityResources(100);
        ct.update(resources);
        int initialProduction = resources.getMoneyProduction();
        ct.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - ct.getProduction()), resources.getMoneyProduction());
    }
    
    @Test
    public void testIsDestroyed() {
    	CommercialTile ct = new CommercialTile();
        CityResources resources = new CityResources(100);
        ct.disassemble(resources);
        Assert.assertEquals(true, ct.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
    	CommercialTile ct1 = new CommercialTile();
        CityResources resources = new CityResources(100);
        ct1.update(resources);
        ct1.disassemble(resources);
        CommercialTile ct2 = new CommercialTile();
        ct2.update(resources);
        ct2.disassemble(resources);
        Assert.assertEquals(true, ct1.equals(ct2));
    }
    
    
}
