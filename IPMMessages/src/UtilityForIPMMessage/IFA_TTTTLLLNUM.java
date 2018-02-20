package UtilityForIPMMessage;

import org.jpos.iso.IFA_LLLCHAR;
import org.jpos.iso.IFA_LLLNUM;

import org.jpos.iso.ISOFieldPackager;
import org.jpos.iso.TaggedFieldPackagerBase;

public class IFA_TTTTLLLNUM extends TaggedFieldPackagerBase{

	 @Override
	    protected int getTagNameLength() {
	        return 4;
	    }

	    protected ISOFieldPackager getDelegate(int length, String description) {
	        return new IFA_LLLNUM(length, description);
	    }
	
}
