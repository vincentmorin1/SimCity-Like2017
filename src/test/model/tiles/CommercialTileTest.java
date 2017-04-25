	package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.CommercialTile;

public class CommercialTileTest {
    
    @Test
    public void testInit() {
        CommercialTile ppt = new CommercialTile();
        Assert.assertEquals(CommercialTile.DEFAULT_CONSUMPTION_CAPACITY, ppt.getProductionCapacity());
        ppt = new CommercialTile(10);
        Assert.assertEquals(10, ppt.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
    	CommercialTile ppt = new CommercialTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getEnergyProduction();
        ppt.update(resources);
        Assert.assertEquals(initialValue + ppt.getProduction(), resources.getEnergyProduction());
    }
    
    @Test
    public void testDisassemble() {
    	CommercialTile ppt = new CommercialTile();
        CityResources resources = new CityResources(100);
        ppt.update(resources);
        int initialProduction = resources.getEnergyProduction();
        ppt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - ppt.getProductionCapacity()), resources.getEnergyProduction());
    }
    
    @Test
    public void testIsDestroyed() {
    	CommercialTile ppt = new CommercialTile();
        CityResources resources = new CityResources(100);
        ppt.disassemble(resources);
        Assert.assertEquals(true, ppt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
    	CommercialTile ppt1 = new CommercialTile();
        CityResources resources = new CityResources(100);
        ppt1.update(resources);
        ppt1.disassemble(resources);
        CommercialTile ppt2 = new CommercialTile();
        ppt2.update(resources);
        ppt2.disassemble(resources);
        Assert.assertEquals(true, ppt1.equals(ppt2));
    }
    
    
}
