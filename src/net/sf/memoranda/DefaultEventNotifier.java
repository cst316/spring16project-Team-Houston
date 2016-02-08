/**
 * DefaultEventNotifier.java Created on 10.03.2003, 21:18:02 Alex Package:
 * net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net Copyright (c) 2003
 *         Memoranda Team. http://memoranda.sf.net
 */
package net.sf.memoranda;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.ui.EventNotificationDialog;

/**
 *  
 */
/*$Id: DefaultEventNotifier.java,v 1.4 2004/01/30 12:17:41 alexeya Exp $*/
public class DefaultEventNotifier implements EventNotificationListener {

	/**
	 * Constructor for DefaultEventNotifier.
	 */
	public DefaultEventNotifier() {
		super();
	}

	/**
	 * @see net.sf.memoranda.EventNotificationListener#eventIsOccured(net.sf.memoranda.Event)
	 */
	public void eventIsOccured(Event ev) {		
		EventNotificationDialog end = new EventNotificationDialog("Memoranda event", ev);
		end.setVisible(true);
		if (end.isSnoozed()) {
			Calendar rightMeow = CurrentDate.get().getCalendar();
			String selectedSnooze = end.getSnoozeDuration();
			int snoozeOffset = Integer.parseInt(selectedSnooze.split(" ")[0]);
			if (selectedSnooze.matches("\\d+\\shours?"))
				rightMeow.add(Calendar.HOUR_OF_DAY, snoozeOffset);
			else
				rightMeow.add(Calendar.MINUTE, snoozeOffset);
			EventsManager.createEvent(new CalendarDate(rightMeow), rightMeow.get(Calendar.HOUR_OF_DAY), rightMeow.get(Calendar.MINUTE), ev.getText(), ev.getTimesSnoozed()+1);
			if (!ev.isRepeatable())
				EventsManager.removeEvent(ev);
			EventsManager.saveEvents();
		}
	}
	/**
	 * @see net.sf.memoranda.EventNotificationListener#eventsChanged()
	 */
	public void eventsChanged() {
		//
	}

	
}
