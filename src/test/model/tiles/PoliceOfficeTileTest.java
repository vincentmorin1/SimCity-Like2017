package test.model.tiles;

import org.junit.Assert;
import org.junit.Test;

import model.CityResources;
import model.tiles.PoliceOfficeTile;

public class PoliceOfficeTileTest {

	@Test
    public void testInit() {
        PoliceOfficeTile pot = new PoliceOfficeTile();
        Assert.assertEquals(PoliceOfficeTile.DEFAULT_ENERGY_CONSUMPTION, pot.getMaxNeededEnergy());
        Assert.assertEquals(PoliceOfficeTile.DEFAULT_NUMBER_WORKERS_MAX, pot.getNumberWorkersMax());
        pot = new PoliceOfficeTile(10,0, 0);
        Assert.assertEquals(10, pot.getMaxNeededEnergy());
    }
    
    @Test
    public void testIsDestroyed() {
        PoliceOfficeTile pot = new PoliceOfficeTile();
        CityResources resources = new CityResources(100);
        pot.disassemble(resources);
        Assert.assertEquals(true, pot.isDestroyed());
    }
    
    
    @Test
    public void testDisassemble() {
    	PoliceOfficeTile pot = new PoliceOfficeTile();
        CityResources resources = new CityResources(100);
        pot.update(resources);
        int initialValue = resources.getUnworkingSeniorPopulation();
        pot.disassemble(resources);
        Assert.assertEquals(initialValue - pot.getNumberWorkers(),resources.getUnworkingSeniorPopulation());
    }
    
    @Test
    public void testIsEquals() {
        PoliceOfficeTile pot1 = new PoliceOfficeTile();
        CityResources resources = new CityResources(100);
        pot1.update(resources);
        pot1.disassemble(resources);
        PoliceOfficeTile pot2 = new PoliceOfficeTile();
        pot2.update(resources);
        pot2.disassemble(resources);
        Assert.assertEquals(true, pot1.equals(pot2));
    }
}
