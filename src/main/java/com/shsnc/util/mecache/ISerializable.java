package com.shsnc.util.mecache;

import java.io.IOException;

public abstract interface ISerializable
{
  public abstract byte[] object2bytes(Object paramObject)
    throws IOException;
  
  public abstract Object bytes2object(byte[] paramArrayOfByte)
    throws IOException, ClassNotFoundException;
}
