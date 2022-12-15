package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        Set<Long> origIds = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        long timeHashMapId = getTimeToGetIds(shortener1, origStrings, origIds);
        long timeHashBiMapId = getTimeToGetIds(shortener2, origStrings, origIds);

        Assert.assertTrue(timeHashMapId > timeHashBiMapId);

        long timeHasMapString = getTimeToGetStrings(shortener1, origIds, origStrings);
        long timeHasBiMapString = getTimeToGetStrings(shortener2, origIds, origStrings);

        Assert.assertEquals(timeHasMapString, timeHasBiMapString, 30);
    }

    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date dateStart = new Date();
        for (String string : strings) {
            ids.add(shortener.getId(string));
        }
        Date dateFinish = new Date();
        return dateFinish.getTime() - dateStart.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date dateStart = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date dateFinish = new Date();
        return dateFinish.getTime() - dateStart.getTime();
    }
}
