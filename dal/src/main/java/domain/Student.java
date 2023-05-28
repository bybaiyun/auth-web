package domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName student
 */
@Data
@Builder
public class Student implements Serializable {
    /**
     * 
     */
    private String sid;

    /**
     * 
     */
    private String sName;

    /**
     * 
     */
    private Date sAge;

    /**
     * 
     */
    private String sSex;

    private static final long serialVersionUID = 1L;
}