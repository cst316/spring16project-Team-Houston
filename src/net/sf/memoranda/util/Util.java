/**
 * Util.java
 * Created on 11.02.2003, 23:59:21 Alex
 * Package: net.sf.memoranda.util
 *
 * @author Alex V. Alishevskikh, alex@openmechanics.net
 * Copyright (c) 2003 Memoranda team: http://memoranda.sf.net
 */
package net.sf.memoranda.util;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;

import javax.swing.JFileChooser;

import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.ui.App;
import net.sf.memoranda.ui.AppFrame;
import net.sf.memoranda.ui.ExceptionDialog;
import java.util.Random;

/**
 *
 */
/*$Id: Util.java,v 1.13 2007/03/20 08:22:41 alexeya Exp $*/
public class Util {

	static long seed = 0;
	
    public static String generateId() {
        long seed1 = System.currentTimeMillis();
        while (seed1 == seed) 
        	seed1 = System.currentTimeMillis(); // Make sure we'll don't get the same seed twice		  
    	seed = seed1;        	
    	Random r = new Random(seed); 
    	return Integer.toString(r.nextInt(), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16) +
				"-"+Integer.toString(r.nextInt(65535), 16);
                    
    }
    
    /*
     * Used for converting a time String of Format "hh:mm aa" to 24 hour 
     * time String of format "HH:mm"
     * 
     * Victor Best 1/2016
     */
    public static String getMilitaryTime(String time){
    	
    	if(time.length() == 0){
    		return time;
    	}
    	
    	String milTime = "";   	
    	String hours = time.substring(0, time.indexOf(":"));
    	String minutes = time.substring((time.indexOf(":") + 1), (time.length() - 2));
    	
    	int hrs = Integer.parseInt(hours);
    	
    	if(time.contains("PM")){
    		hrs += 12;
    	}
    	
    	milTime = hrs + ":" + minutes;
    	return milTime;
    }
    
    public static String getDateStamp(Calendar cal) {
        return cal.get(Calendar.DAY_OF_MONTH)
            + "/"
            + (cal.get(Calendar.MONTH))
            + "/"
            + new Integer(cal.get(Calendar.YEAR)).toString();

    }

    public static String getDateStamp(CalendarDate date) {
        return Util.getDateStamp(date.getCalendar());
    }

    public static int[] parseDateStamp(String s) {
        s = s.trim();
        int i1 = s.indexOf("/");
        int i2 = s.indexOf("/", i1 + 1);
        int[] date = new int[3];
        date[0] = new Integer(s.substring(0, i1)).intValue();
        date[1] = new Integer(s.substring(i1 + 1, i2)).intValue();
        date[2] = new Integer(s.substring(i2 + 1)).intValue();
        return date;
        /*DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale);
        Date d = null;
        try {
            d = dateFormat.parse(s);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new int[3];
        }
        int[] ret = {d.getDay(), d.getMonth(), d.getYear()};
        return ret;*/
    }

    public static String getEnvDir() {
    	// Changed static building of getEnvDir
    	// Now system-related path-separator is used
		String p = System.getProperty("user.home") + File.separator 
			+ ".jnotes2" + File.separator;
        if (new File(p).isDirectory()) return p;
        return System.getProperty("user.home") + File.separator 
        	+ ".memoranda" + File.separator;
    }

    public static String getCDATA(String s) {
      return "<![CDATA["+s+"]]>";
    }
    
    public static void runBrowser(String url) {
        if (!checkBrowser())
            return;
        String commandLine = MimeTypesList.getAppList().getBrowserExec()+" "+url;
        System.out.println("Run: " + commandLine);
        try {
            /*DEBUG*/
            Runtime.getRuntime().exec(commandLine);
        }
        catch (Exception ex) {
            new ExceptionDialog(ex, "Failed to run an external web-browser application with commandline<br><code>"
                    +commandLine+"</code>", "Check the application path and command line parameters " +
                    		"(File-&gt;Preferences-&gt;Resource types).");
        }
    }
    
    public static boolean checkBrowser() {
        AppList appList = MimeTypesList.getAppList();
        String bpath = appList.getBrowserExec();
        if (bpath != null)
            if (new File(bpath).isFile())
                return true;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileHidingEnabled(false);
        chooser.setDialogTitle(Local.getString("Select the web-browser executable"));
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        /*java.io.File lastSel = (java.io.File) Context.get("LAST_SELECTED_RESOURCE_FILE");
        if (lastSel != null)
            chooser.setCurrentDirectory(lastSel);*/
        if (chooser.showOpenDialog(App.getFrame()) != JFileChooser.APPROVE_OPTION)
            return false;
        appList.setBrowserExec(chooser.getSelectedFile().getPath());
        CurrentStorage.get().storeMimeTypesList();
        return true;
    }
    
    public static String getHoursFromMillis(long ms) {
    	double numSeconds = (((double) ms) / 1000d);
    	return String.valueOf(numSeconds / 3600);
    }
    
    public static long getMillisFromHours(String hours) {
    	try {
        	double numHours = Double.parseDouble(hours);
        	double millisDouble = (numHours * 3600 * 1000);
        	return (long) millisDouble;
    	}
    	catch (NumberFormatException e) {
    		return 0;
    	}
    }
    
    static Set tempFiles = new HashSet();
    
    static {
    	AppFrame.addExitListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (Iterator i = tempFiles.iterator(); i.hasNext();) 
					((File)i.next()).delete();				}
			});
    }
    
    public static File getTempFile() throws IOException {
    	File f = File.createTempFile("tmp", ".html", null);
    	tempFiles.add(f);
    	return f;
    }
    
    public static void debug(String str) {
    	System.out.println("[DEBUG] " + str);
    }

    /**
     * @param e
     */
    public static void error(Exception e) {
        System.out.println("[ERROR] Exception: " + e.getClass().getName());
        System.out.println("[ERROR] Exception Message: " + e.getMessage());
        
        String stackTrace = "";
        StackTraceElement[] ste = e.getStackTrace();
        for (int i = 0; i < ste.length; i++) {
            stackTrace = ste[i].toString() + "\n";
        }
        System.out.println("[ERROR] Stack Trace: " + stackTrace);
    }
    
    /**
     * The following translucency support check code utilized from:
     * https://docs.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
     * 
     * @return true if translucency is supported
     */
    public static boolean checkTranslucentSupport() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		
		return gd.isWindowTranslucencySupported(java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT);
	}
    
    /**
     * Returns a formatted string representing time difference duration. 
     * The returned string is formatted according to the format specified.
     * If no format is specified, returned format is "HMS" (HH:MM:SS).
     * @param timeDifference long representing time delta in nanoseconds
     * @param timeFormat string containing return value formatting. Valid values are "D", "H", "M", "S"
     * 
     * @return string representing time duration in specified timeFormat
     * @see System.nanoTime()
     */
    public static String formatTimeDuration(Long timeDifference, String timeFormat) {
    	final String acceptableFormats = "dhms";
    	if (timeFormat == null || timeFormat.isEmpty())
    		timeFormat = "HMS";
    	TimeUnit[] unitList = {TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS};
    	long[] timeValues = new long[4];
		StringBuilder returnValue = new StringBuilder();
		timeFormat = timeFormat.toLowerCase();
		for (int i = 0; i < acceptableFormats.length(); i++) {
			if (i > 0) {
				timeDifference -= TimeUnit.NANOSECONDS.convert(timeValues[i-1], unitList[i-1]);
			}
			if (timeFormat.contains(String.valueOf(acceptableFormats.charAt(i)))) {
				timeValues[i] = unitList[i].convert(timeDifference, TimeUnit.NANOSECONDS);
				returnValue.append(String.format("%02d:", timeValues[i]));
			}
		}
		if (!returnValue.toString().isEmpty())
			return returnValue.substring(0, returnValue.length()-1);
		return "";
	}
    
    public static String formatTimeDuration(Long timeDifference) {
    	return formatTimeDuration(timeDifference, null);
    }
    
}
