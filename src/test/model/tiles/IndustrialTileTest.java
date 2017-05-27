	package test.model.tiles;
import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.IndustrialTile;

public class IndustrialTileTest {
    
    @Test
    public void testInit() {
        IndustrialTile it = new IndustrialTile();
        Assert.assertEquals(IndustrialTile.DEFAULT_PRODUCTION_CAPACITY, it.getProductionCapacity());
        Assert.assertEquals(IndustrialTile.DEFAULT_MAX_NEEDED_ENERGY, it.getMaxNeededEnergy());
        Assert.assertEquals(IndustrialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION, it.getEvolutionEnergyConsumption());
        Assert.assertEquals(IndustrialTile.DEFAULT_WORKERS_CAPACITY, it.getInhabitantsCapacity());
        it = new IndustrialTile(10,0,0);
        Assert.assertEquals(10, it.getProductionCapacity());
    }
    
    @Test
    public void testUpdate() {
    	IndustrialTile it = new IndustrialTile();
        CityResources resources = new CityResources(100);
        int initialValue = resources.getProductsCount();
        it.update(resources);
        Assert.assertEquals(initialValue + it.getProduction(), resources.getProductsCount());
    }
    
    @Test
    public void testDisassemble() {
    	IndustrialTile it = new IndustrialTile();
        CityResources resources = new CityResources(100);
        it.update(resources);
        int initialProduction = resources.getProductsCount();
        it.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialProduction - it.getProduction()), resources.getProductsCount());
    }
    
    @Test
    public void testIsDestroyed() {
    	IndustrialTile it = new IndustrialTile();
        CityResources resources = new CityResources(100);
        it.disassemble(resources);
        Assert.assertEquals(true, it.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
    	IndustrialTile it1 = new IndustrialTile();
        CityResources resources = new CityResources(100);
        it1.update(resources);
        it1.disassemble(resources);
        IndustrialTile it2 = new IndustrialTile();
        it2.update(resources);
        it2.disassemble(resources);
        Assert.assertEquals(true, it1.equals(it2));
    }
    
    
}
