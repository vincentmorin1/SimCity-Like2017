package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.HospitalTile;


public class HospitalTileTest {

	@Test
    public void testInit() {
        HospitalTile ht = new HospitalTile();
        Assert.assertEquals(HospitalTile.DEFAULT_NUMBER_WORKERS_MAX, ht.getNumberWorkersMax());
        Assert.assertEquals(HospitalTile.DEFAULT_ENERGY_CONSUMPTION, ht.getMaxNeededEnergy());
        ht = new HospitalTile(10,0, 0);
        Assert.assertEquals(10, ht.getMaxNeededEnergy());
    }
    
	@Test
    public void testIsDestroyed() {
    	HospitalTile ht = new HospitalTile();
        CityResources resources = new CityResources(100);
        ht.disassemble(resources);
        Assert.assertEquals(true, ht.isDestroyed());
    }
    
    @Test
    public void testDisassemble() {
    	HospitalTile ht = new HospitalTile();
        CityResources resources = new CityResources(100);
        ht.update(resources);
        int initialValue = resources.getUnworkingSeniorPopulation();
        ht.disassemble(resources);
        Assert.assertEquals(initialValue - ht.getNumberWorkers(),resources.getUnworkingSeniorPopulation());
    }
    
    @Test
    public void testIsEquals() {
    	HospitalTile ht1 = new HospitalTile();
        CityResources resources = new CityResources(100);
        ht1.update(resources);
        ht1.disassemble(resources);
        HospitalTile ht2 = new HospitalTile();
        ht2.update(resources);
        ht2.disassemble(resources);
        Assert.assertEquals(true, ht1.equals(ht2));
    }
}
