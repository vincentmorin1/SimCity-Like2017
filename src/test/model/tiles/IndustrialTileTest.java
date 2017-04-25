	package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.IndustrialTile;

public class IndustrialTileTest {
    
    @Test
    public void testInit() {
        IndustrialTile ppt = new IndustrialTile();
        Assert.assertEquals(IndustrialTile.DEFAULT_PRODUCTION_CAPACITY, ppt.getProductionCapacity());
        ppt = new IndustrialTile(10);
        Assert.assertEquals(10, ppt.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
    	IndustrialTile ppt = new IndustrialTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getEnergyProduction();
        ppt.update(resources);
        Assert.assertEquals(initialValue + ppt.getProduction(), resources.getEnergyProduction());
    }
    
    @Test
    public void testDisassemble() {
    	IndustrialTile ppt = new IndustrialTile();
        CityResources resources = new CityResources(100);
        ppt.update(resources);
        int initialProduction = resources.getEnergyProduction();
        ppt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - ppt.getProductionCapacity()), resources.getEnergyProduction());
    }
    
    @Test
    public void testIsDestroyed() {
    	IndustrialTile ppt = new IndustrialTile();
        CityResources resources = new CityResources(100);
        ppt.disassemble(resources);
        Assert.assertEquals(true, ppt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
    	IndustrialTile ppt1 = new IndustrialTile();
        CityResources resources = new CityResources(100);
        ppt1.update(resources);
        ppt1.disassemble(resources);
        IndustrialTile ppt2 = new IndustrialTile();
        ppt2.update(resources);
        ppt2.disassemble(resources);
        Assert.assertEquals(true, ppt1.equals(ppt2));
    }
    
    
}
