package com.vgoryashko.connectarrays;

/*
 *Class that tests class which performs conncetion of two arrays sorted in sccending order
 *@author vgoryashko
 *@since 31.10.2016
 *@version 1.0
 */

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConnectArraysTest{

    @Test
    public void connectArraysTest(){
	ConnectArrays connect = new ConnectArrays();
        assertThat(connect.connectArrays(new int[]{0, 1, 2, 3}, new int[]{5, 7, 8, 11}), is(new int[]{0, 1, 2, 3, 5, 7, 8, 11}));
    }
}
