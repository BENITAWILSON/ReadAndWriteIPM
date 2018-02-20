package UtilityForIPMMessage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;

public class CreateIPMMessage {

	
	public static void main(String[] args) throws IOException, ISOException {
		// Create Packager based on XML that contain DE type
		GenericPackager packager = new GenericPackager("ISO8583_format.xml");

		// Create ISO Message
		ISOMsg isoMsg = new ISOMsg();
		isoMsg.setPackager(packager);
		isoMsg.setMTI("1644");
		isoMsg.set(24, "697");
		isoMsg.set(48, "010502500217081000000999674000010122001T");
		isoMsg.set(71, "00000000");
		/*isoMsg.set(11, "123456");
		isoMsg.set(44, "A5DFGR");
		isoMsg.set(105, "ABCDEFGHIJ 1234567890");*/

		// print the DE list
		logISOMsg(isoMsg);

		// Get and print the output result
		byte[] data = isoMsg.pack();
		File f= new File("C:/Users/bcharleywilson/Desktop/H1 2018/samplemsg.ipm");
		FileOutputStream fo=new FileOutputStream(f);
		fo.write(data);
		System.out.println("RESULT : " + new String(data));
	}

	private static void logISOMsg(ISOMsg msg) {
		System.out.println("----ISO MESSAGE-----");
		try {
			System.out.println("  MTI : " + msg.getMTI());
			for (int i=1;i<=msg.getMaxField();i++) {
				if (msg.hasField(i)) {
					System.out.println("    Field-"+i+" : "+msg.getString(i));
				}
			}
		} catch (ISOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("--------------------");
		}

}


}
