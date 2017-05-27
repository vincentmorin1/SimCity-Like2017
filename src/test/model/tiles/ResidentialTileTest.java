package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.ResidentialTile;

public class ResidentialTileTest {

	@Test
    public void testInit() {
        ResidentialTile rt = new ResidentialTile();
        Assert.assertEquals(ResidentialTile.DEFAULT_EVOLUTION_ENERGY_CONSUMPTION, rt.getEvolutionEnergyConsumption());
        Assert.assertEquals(ResidentialTile.DEFAULT_INHABITANTS_CAPACITY, rt.getInhabitantsCapacity());
        Assert.assertEquals(ResidentialTile.DEFAULT_MAX_NEEDED_ENERGY, rt.getMaxNeededEnergy());
        rt = new ResidentialTile(0,0);
        Assert.assertEquals(0, rt.getTopLeftCornerX());
        Assert.assertEquals(0, rt.getTopLeftCornerY());
    }
    
    @Test
    public void testDisassemble() {
        ResidentialTile rt = new ResidentialTile();
        CityResources resources = new CityResources(100);
        rt.update(resources);
        int initialValue = resources.getPopulation();
        rt.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialValue - rt.getInhabitantsCapacity()), resources.getPopulation());
    }
    
    @Test
    public void testIsDestroyed() {
        ResidentialTile rt = new ResidentialTile();
        CityResources resources = new CityResources(100);
        rt.disassemble(resources);
        Assert.assertEquals(true, rt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        ResidentialTile rt1 = new ResidentialTile();
        CityResources resources = new CityResources(100);
        rt1.update(resources);
        rt1.disassemble(resources);
        ResidentialTile rt2 = new ResidentialTile();
        rt2.update(resources);
        rt2.disassemble(resources);
        Assert.assertEquals(true, rt1.equals(rt2));
    }
}
