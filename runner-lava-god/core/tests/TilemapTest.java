import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.pongamoshuevos.map.Tile;
import com.pongamoshuevos.map.Tilemap;


public class TilemapTest {
	Tilemap tilemap = new Tilemap(null);
	
	@Test
	public void tilemapAlwaysHasMaxTiles() {
		assertEquals((int)Tilemap.MAXTILES,(int) tilemap.getTiles().size());
	}
	
	@Test 
	public void tilemapCanCreateNewTiles(){
		LinkedList<Tile> old = (LinkedList<Tile>)tilemap.getTiles().clone();
		tilemap.update();
		assertNotSame(old, tilemap.getTiles());
	}
	
	@Test
	public void tilemapIsVisibleByDefault(){
		assertTrue(tilemap.visible());
	}
	
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test(expected = RuntimeException.class) 
	public void test(){
		tilemap.isOnTile(2).geteffect();
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Soy un tile colisionable");
	}

}
