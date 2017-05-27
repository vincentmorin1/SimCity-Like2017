package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.BeachTile;

public class BeachTileTest {
	
	@Test
    public void testInit() {
        BeachTile bt = new BeachTile();
        Assert.assertEquals(BeachTile.DEFAULT_NUMBER_TOURISTS_MAX, bt.getNumberTouristsMax());
        Assert.assertEquals(BeachTile.DEFAULT_ENERGY_CONSUMPTION, bt.getEnergyConsumption());
        bt = new BeachTile(10,0, 0);
        Assert.assertEquals(10, bt.getNumberTouristsMax());
        Assert.assertEquals(10, bt.getEnergyConsumption());
    }
    
    @Test
    public void testUpdate() {
    	BeachTile bt = new BeachTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getNumberSeniorWithoutLeisure() + resources.getNumberStudentWithoutLeisure();
        bt.update(resources);
        Assert.assertEquals(initialValue - bt.getNumberTourists(), resources.getNumberSeniorWithoutLeisure() + resources.getNumberStudentWithoutLeisure());
    }
    
    @Test
    public void testIsDestroyed() {
    	BeachTile bt = new BeachTile();
        CityResources resources = new CityResources(100);
        bt.disassemble(resources);
        Assert.assertEquals(true, bt.isDestroyed());
    }
    
    @Test
    public void testDisassemble() {
    	BeachTile bt = new BeachTile();
        CityResources resources = new CityResources(100);
        bt.update(resources);
        int initialValue = resources.getNumberSeniorWithoutLeisure() + resources.getNumberStudentWithoutLeisure();
        bt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialValue - bt.getNumberTourists()), resources.getNumberSeniorWithoutLeisure() + resources.getNumberStudentWithoutLeisure());
    }
    
    @Test
    public void testIsEquals() {
    	BeachTile bt1 = new BeachTile();
        CityResources resources = new CityResources(100);
        bt1.update(resources);
        bt1.disassemble(resources);
        BeachTile bt2 = new BeachTile();
        bt2.update(resources);
        bt2.disassemble(resources);
        Assert.assertEquals(true, bt1.equals(bt2));
    }
}
