package students.web.tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

public final class HelloTag extends TagSupport {
    
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void release() {
        super.release();
        name = null;
    }
    
    public int doStartTag() throws JspTagException {
        
        try {
            pageContext.getOut().write("Starting!");
            if (name == null) {
                pageContext.getOut().write("Hello World!");
            } else {
                pageContext.getOut().write("Hello World! I'm " + name);
            }
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        
        return SKIP_BODY;
        
    }
    
}