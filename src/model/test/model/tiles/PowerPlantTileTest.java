package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.PowerPlantTile;

public class PowerPlantTileTest {
    
    @Test
    public void testInit() {
        PowerPlantTile ppt = new PowerPlantTile();
        Assert.assertEquals(PowerPlantTile.DEFAULT_PRODUCTION_CAPACITY, ppt.getProductionCapacity());
        ppt = new PowerPlantTile(10);
        Assert.assertEquals(10, ppt.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
        PowerPlantTile ppt = new PowerPlantTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getEnergyProduction();
        ppt.update(resources);
        Assert.assertEquals(initialValue + ppt.getProduction(), resources.getEnergyProduction());
    }
    
    @Test
    public void testDisassemble() {
        PowerPlantTile ppt = new PowerPlantTile();
        CityResources resources = new CityResources(100);
        ppt.update(resources);
        int initialProduction = resources.getEnergyProduction();
        ppt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - ppt.getProductionCapacity()), resources.getEnergyProduction());
    }
    
    @Test
    public void testIsDestroyed() {
        PowerPlantTile ppt = new PowerPlantTile();
        CityResources resources = new CityResources(100);
        ppt.disassemble(resources);
        Assert.assertEquals(true, ppt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        PowerPlantTile ppt1 = new PowerPlantTile();
        CityResources resources = new CityResources(100);
        ppt1.update(resources);
        ppt1.disassemble(resources);
        PowerPlantTile ppt2 = new PowerPlantTile();
        ppt2.update(resources);
        ppt2.disassemble(resources);
        Assert.assertEquals(true, ppt1.equals(ppt2));
    }
    
    
}
