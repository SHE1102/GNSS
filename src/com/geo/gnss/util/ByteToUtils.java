package com.geo.gnss.util;

public class ByteToUtils {

	public static int byteToInt(byte[] b) {
		int value = 0;
		for (int i = 0; i < b.length; i++) {
			int n = (b[i] < 0 ? (int) b[i] + 256 : (int) b[i]) << (8 * i);
			value += n;
		}
		return value;
	}
	
	public static double byteToDouble(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long) (arr[i] & 0xff)) << (8 * i);
		}
		return Double.longBitsToDouble(value);
	}

	public static float byteTofloat(byte[] b) {
		int l;
		l = b[0];
		l &= 0xff;
		l |= ((long) b[1] << 8);
		l &= 0xffff;
		l |= ((long) b[2] << 16);
		l &= 0xffffff;
		l |= ((long) b[3] << 24);
		return Float.intBitsToFloat(l);
	}
	
}
