package UtilityForIPMMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jpos.iso.ISOBinaryField;
import org.jpos.iso.ISOComponent;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOField;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class ReadIPMMessage {

	private static final String Filename = "C:/Users/bcharleywilson/Desktop/H1 2018/samplemsg.ipm";

	public static void main(String[] args) throws IOException, ISOException {

		String filedata;
		File file = new File(Filename);
		int l = 0;
		RandomAccessFile fs = null;
		byte[] bFile = new byte[(int) file.length()];
		 System.out.println(bFile.length);
		try {
			// convert file into array of bytes
			fs = new RandomAccessFile(file, "r");
			while (bFile.length > l) {

				fs.read(bFile);
				// System.out.println(new String(bFile));

				GenericPackager packager = new GenericPackager("ISO8583_format.xml");
			
				ISOMsg isoMsg = new ISOMsg();
				isoMsg.setPackager(packager);
				isoMsg.unpack(bFile);
				//isoMsg.dump(System.out, null);
				logISOMsg(isoMsg);
				// Map map1=new HashMap();
				// map1=isoMsg.getChildren();

				byte[] packedmsgsize = isoMsg.pack();
				l = l + (int) packedmsgsize.length;

				// System.out.println(l);
				fs.seek(l);
				// System.out.println(fileInputStream.getFilePointer());
			}
		} finally {
		}
	}

	private static void logISOMsg(ISOMsg msg) {
		Collection m= new ArrayList<>();
		
		System.out.println("----------------ISO MESSAGE------------------");
		try {
			System.out.println("  MTI : " + msg.getMTI());
			//System.out.println(msg.getValue(1));
			for (int i=1;i<=msg.getMaxField();i++) 
			{
				if (msg.hasField(i)) 
				{
					System.out.println(" Field-"+i+" : "+msg.getString(i));
					
					if( msg.getComponent(i).getChildren().size()>0)
					{
					ISOMsg msgnew= (ISOMsg) msg.getComponent(i);
					for (int a=0;a<=msgnew.getMaxField();a++) 
					{
						
						if (msgnew.hasField(a)) 
						{
							System.out.println("   Sub Field-"+a+" : "+msgnew.getString(a));
							
						}
					}
					
					/*ISOComponent c = msg.getComponent(i);
					//System.out.println("---printing the dump----");
					//c.dump(System.out, null);
					//System.out.println("---End of the dump----");
					//System.out.println(" component" +i+" :"+c.getChildren().size());
			
					if(c.getChildren().size()>0)
					{
						
						System.out.println("inside if");
						
						m=c.getChildren().values();
						Iterator itr=m.iterator();
						while(itr.hasNext()){
						
						
						 ISOField c1=(ISOField) itr.next();
						 byte[] b=new byte[100];
						 b=c1.getBytes();
						 String s= new String(b);
						 System.out.println("Sub field in bytes " +c1.getBytes().toString()+ " Subfield in string"+s);
					 }*/
				}
			} 
				
			}
			
		}catch(

	ISOException e)

	{
		e.printStackTrace();
	} finally

	{
		System.out.println("---------------------------------------------");
	}

}

}
