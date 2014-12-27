package com.pongamoshuevos.map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.pongamoshuevos.map.tiles.ATile;
import com.pongamoshuevos.map.tiles.BTile;

public final class TileGenerator {
	@SuppressWarnings("unchecked")
	private static final List<Class<? extends Tile>> TILETYPES = Arrays.asList(
			ATile.class, BTile.class);

	public static Tile generateNext(LinkedList<Tile> status) {
		try {
			Tile last = status.getLast();
			if (last.getClass().equals(ATile.class) && status.size() > 0) {
				return new BTile(0);
			} else {
				return new ATile(0);
			}
		} catch (NoSuchElementException e) {
			return new ATile(0);
		}
	}
}
