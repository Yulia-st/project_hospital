package com.my.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public class Greeting extends TagSupport{
private static final long serialVersionUID = 1L;
    
	@Override
    public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print( "Wellcome!" );
		} catch (IOException e) {
			
			e.printStackTrace();
			throw new JspTagException("ErrorTag: " + e.getMessage());
		}
		return SKIP_BODY;
		
	}
}
