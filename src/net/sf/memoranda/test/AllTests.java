package net.sf.memoranda.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ EventImplTest.class, 
		EventsSchedulerTest.class,
		HistoryItemTest.class, 
		MiscTrackingListTest.class, 
		MiscTrackingTest.class,
		ProjectManagerTest.class, 
		ResourcesListImplTest.class, 
		ResourceTest.class,
		TaskImplTest.class, 
		TaskListImplTest.class
})
public class AllTests {

}
