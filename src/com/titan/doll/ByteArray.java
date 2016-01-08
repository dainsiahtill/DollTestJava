package com.titan.doll;

import java.util.Vector;

public class ByteArray
{
	public int bytesAvailable;
	public int position;
	
	private Vector<Integer> _vector;
	
	public ByteArray()
	{
		_vector = new Vector<Integer>();
		
		position = 0;
		bytesAvailable = 0;
	}
	
	public ByteArray(int[] bytes) 
	{
		_vector = new Vector<Integer>();
		position = 0;
		
		for (int i = 0; i < bytes.length; i++) 
		{
			set(i, bytes[i]);
		}
		
		bytesAvailable = _vector.size();
	}

	public ByteArray(byte[] bytes) 
	{
		_vector = new Vector<Integer>();
		position = 0;
		
		for (int i = 0; i < bytes.length; i++) 
		{
			set(i, bytes[i] & 0xff);
		}
		
		bytesAvailable = _vector.size();
	}

	public void set(int location, int value)
	{
		int size = _vector.size();
		if (location < size)
		{
			_vector.set(location, value);
		}
		else
		{
			int c =  location - (size - 1);
			if (c == 1)
			{
				_vector.add(value);
			}
			else
			{
				throw new IllegalAccessError("Ë÷Òý³¬³ö·¶Î§");
			}
		}
	}
	
	public int get(int location)
	{
		return _vector.get(location);
	}
	
	public void writeBytes(byte[] bytes, int offset, int length)
	{
		length = length == 0?bytes.length:length;
		for (int i = offset; i < offset + length; i++) 
		{
			writeByte(bytes[i]);
		}
	}
	
	public void writeBytes(ByteArray bytes, int offset, int length)
	{
		length = length == 0?bytes.getLength():length;
		for (int i = offset; i < offset + length; i++) 
		{
			writeByte(bytes.get(i) & 0xff);
		}
	}
	
	public void writeByte(int bt)
	{
		set(position++, bt);
	}
	
	public int readByte() 
	{
		return get(position++);
	}
	
	public void writeInt(int value)
	{
		writeByte((value >> 24) & 0xFF);
		writeByte((value >> 16) & 0xFF);
		writeByte((value >>  8) & 0xFF);
		writeByte((value      ) & 0xFF);
	}
	
	public int readInt() 
	{
		return (readByte() << 24) | (readByte() << 16) | (readByte() << 8) | readByte();
	}
	
	public void writeShort(short value)
	{
		writeByte((value >>  8) & 0xFF);
		writeByte((value      ) & 0xFF);
	}
	
	public int getLength()
	{
		return _vector.size();
	}
	
	public byte[] toBytes()
	{
		int size = _vector.size();
		byte[] bytes = new byte[size];
		
		for (int i = 0; i < size; i++) 
		{
			bytes[i] = (byte) (get(i) & 0xff);
		}
		
		return bytes;
	}

	

	

	
}
