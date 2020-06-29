/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SmartClientRequestDataset.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 11.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;



/**
 * <pre>
 * Class : SmartClientRequestDataset
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
@Target({java.lang.annotation.ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface SCRequestDataset {
    public abstract String value();
}
