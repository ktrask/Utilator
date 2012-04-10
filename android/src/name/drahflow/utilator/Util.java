package name.drahflow.utilator;

import android.graphics.*;
import java.text.*;
import java.util.*;

class Util {
	public static int loadInt(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if(o == null) return 0;
		return Integer.parseInt(o.toString());
	}

	public static String loadString(Map<String, Object> map, String key) {
		Object o = map.get(key);
		if(o == null) return "";
		return o.toString();
	}

	// Maps [0, max] into [0, infinity], returns mapped v
	public static long exponentialMap(int v, int max) {
		long res = (long)Math.exp(20 * (float)v / max);
		return res;
	}
	public static long exponentialMap(int v, int max, int w, int maxw) {
		long res = (long)Math.exp(20 * (float)v / max + 0.2f * (float)w / maxw);
		return res;
	}
	public static int reverseExponentialMap(long v, int max) {
		return (int)(Math.log(v) * max / 20);
	}

	static SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat isoFullDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static public String humanDate(long time) {
		return isoDateFormat.format(new Date(time));
	}
	static public String isoDate(long time) {
		return isoDate(new Date(time));
	}
	static public String isoDate(Date d) {
		return isoDateFormat.format(d);
	}
	static public String isoFullDate(Date d) {
		return isoFullDateFormat.format(d);
	}

	public static String humanTime(long seconds) {
		if(seconds < 60 * 2) {
			return seconds + "s";
		} else if(seconds < 60 * 60 * 2) {
			return seconds / 60 + "m";
		} else if(seconds < 60 * 60 * 24 * 2) {
			return seconds / 60 / 60 + "h";
		} else if(seconds < 60 * 60 * 12 * 9) {
			return seconds / 60 / 60 / 12 + "d";
		} else if(seconds < 60 * 60 * 10 * 5 * 100) {
			return seconds / 60 / 60 / 10 / 5 + "w";
		} else {
			return seconds / 60 / 60 / 10 / 5 / 52 + "y";
		}
	}

	static public int drawWrapped(Canvas c, String s, int minX, int maxX, int y, Paint p) {
		int x = minX;
		StringBuilder str = new StringBuilder();

		for(String word: s.split(" ")) {
			int w = (int)p.measureText(word);

			if(x + w > maxX) {
				c.drawText(str.toString(), minX, y, p);
				str.delete(0, str.length());

				y += 20;
				x = minX;
			}

			x += w + 4;
			str.append(word);
			str.append(" ");
		}

		c.drawText(str.toString(), minX, y, p);

		return y;
	}
}