package net.avantic.paginatedList.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.avantic.paginatedList.model.Item;
import android.graphics.Color;

public class ItemDAO {
	private static final int TOTAL_ITEMS = 24;
	
	
	private static List<Item> items;

	public static List<Item> getItems(int initialItem, int lastItem) {
		if (items == null) {
			items = new ArrayList<Item>();
			for (int i = 0; i < TOTAL_ITEMS; i++)
				items.add(newRandomItem(i));
		}
		
		return items.subList(initialItem, lastItem);
	}
	
	public static int countItems() {
		return TOTAL_ITEMS;
	}

	private static Item newRandomItem(int i) {
		Item item = new Item();
		item.setTitle("Item " + (i + 1));
		Random random = new Random();
		int randomNumRed = random.nextInt(255);
		int randomNumGreen = random.nextInt(255);
		int randomNumBlue = random.nextInt(255);
		item.setDescription("Ref. " + randomNumRed + "." + randomNumGreen + "." + randomNumBlue);
		item.setColor(Color.rgb(randomNumRed, randomNumGreen, randomNumBlue));
		
		return item;
	}
	
}