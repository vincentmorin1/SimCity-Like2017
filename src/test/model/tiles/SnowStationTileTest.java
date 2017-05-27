package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.SnowStationTile;

public class SnowStationTileTest {

	@Test
    public void testInit() {
        SnowStationTile sst = new SnowStationTile();
        Assert.assertEquals(SnowStationTile.DEFAULT_ENERGY_CONSUMPTION, sst.getMaxNeededEnergy());
        Assert.assertEquals(SnowStationTile.DEFAULT_NUMBER_TOURISTS_MAX, sst.getNumberTouristsMax());
        sst = new SnowStationTile(10,10,0, 0);
        Assert.assertEquals(10, sst.getMaxNeededEnergy());
        Assert.assertEquals(10, sst.getNumberTouristsMax());
    }
    
    @Test
    public void testIsDestroyed() {
        SnowStationTile sst = new SnowStationTile();
        CityResources resources = new CityResources(100);
        sst.disassemble(resources);
        Assert.assertEquals(true, sst.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        SnowStationTile sst1 = new SnowStationTile();
        CityResources resources = new CityResources(100);
        sst1.update(resources);
        sst1.disassemble(resources);
        SnowStationTile sst2 = new SnowStationTile();
        sst2.update(resources);
        sst2.disassemble(resources);
        Assert.assertEquals(true, sst1.equals(sst2));
    }
}
