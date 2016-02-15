package net.sf.memoranda.test.util;

import static org.junit.Assert.*;

import org.junit.Test;

import net.sf.memoranda.util.Pair;
import nu.xom.Element;

public class PairTest {

	@Test
	public void getterSetterTest() {
		Element donnyIsOutOfHis = null;
		Pair pair = new Pair(donnyIsOutOfHis, 1);
		assertEquals(pair.getPriority(), 1);
		assertEquals(pair.getElement(), null);
	}

}
