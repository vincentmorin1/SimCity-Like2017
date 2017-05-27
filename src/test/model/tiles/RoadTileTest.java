package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.RoadTile;

public class RoadTileTest {

	@Test
    public void testInit() {
        RoadTile rt = new RoadTile();
        rt = new RoadTile(0,0);
        Assert.assertEquals(0, rt.getTopLeftCornerX());
        Assert.assertEquals(0, rt.getTopLeftCornerY());
    }
    
    @Test
    public void testIsDestroyed() {
        RoadTile rt = new RoadTile();
        CityResources resources = new CityResources(100);
        rt.disassemble(resources);
        Assert.assertEquals(true, rt.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        RoadTile rt1 = new RoadTile();
        CityResources resources = new CityResources(100);
        rt1.update(resources);
        rt1.disassemble(resources);
        RoadTile rt2 = new RoadTile();
        rt2.update(resources);
        rt2.disassemble(resources);
        Assert.assertEquals(true, rt1.equals(rt2));
    }
}
