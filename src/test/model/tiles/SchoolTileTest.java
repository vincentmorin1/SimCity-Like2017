package test.model.tiles;

import org.junit.Test;

import org.junit.Assert;

import model.CityResources;
import model.tiles.SchoolTile;

public class SchoolTileTest {

	@Test
    public void testInit() {
        SchoolTile st = new SchoolTile();
        Assert.assertEquals(SchoolTile.DEFAULT_ENERGY_CONSUMPTION, st.getMaxNeededEnergy());
        Assert.assertEquals(SchoolTile.DEFAULT_NUMBER_STUDENT_MAX, st.getNumberStudentMax());
        st = new SchoolTile(10,10,0, 0);
        Assert.assertEquals(10, st.getMaxNeededEnergy());
        Assert.assertEquals(10, st.getNumberStudentMax());
    }
    
    @Test
    public void testDisassemble() {
        SchoolTile st = new SchoolTile();
        CityResources resources = new CityResources(100);
        st.update(resources);
        int initialValue = resources.getStudentPopulation();
        st.disassemble(resources);
        Assert.assertEquals(Math.max(0, initialValue - st.getNumberStudent()), resources.getStudentPopulation());
    }
    
    @Test
    public void testIsDestroyed() {
        SchoolTile st = new SchoolTile();
        CityResources resources = new CityResources(100);
        st.disassemble(resources);
        Assert.assertEquals(true, st.isDestroyed());
    }
    
    @Test
    public void testIsEquals() {
        SchoolTile st1 = new SchoolTile();
        CityResources resources = new CityResources(100);
        st1.update(resources);
        st1.disassemble(resources);
        SchoolTile st2 = new SchoolTile();
        st2.update(resources);
        st2.disassemble(resources);
        Assert.assertEquals(true, st1.equals(st2));
    }
}
