/*
* Copyright 2011 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
*  * Comarca generated by UDA 1.0, 29-jul-2011 9:08:11.
* @author UDA
 */

public class Comarca  implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
         private BigDecimal code;
         private Provincia provincia;
         private String descEs;
         private String descEu;
         private String css;
         private List<Localidad> localidads = new ArrayList<Localidad>();

/** Method 'Comarca'.
*
*/
    public Comarca() {
    }
    /** Method 'Comarca'.
    * @param  code BigDecimal
    * @param  descEs String
    * @param  descEu String
    * @param  css String
    * @param  provincia Provincia
    */
    public Comarca(BigDecimal code, String descEs, String descEu, String css , Provincia provincia) {	
        this.code = code;
        this.provincia = provincia;
        this.descEs = descEs;
        this.descEu = descEu;
        this.css = css;
    }

    /** Method 'Comarca'.
    * @param  code BigDecimal
    * @param  descEs String
    * @param  descEu String
    * @param  css String
    * @param  provincia Provincia
    * @param  localidads List<Localidad>
    */
   public Comarca(BigDecimal code, String descEs, String descEu, String css, Provincia provincia, List<Localidad> localidads) {	

           this.code = code;
           this.provincia = provincia;
           this.descEs = descEs;
           this.descEu = descEu;
           this.css = css;
           this.localidads = localidads;
    }

    /**
     * Method 'getCode'.
     *
     * @return BigDecimal
     */
	 
    
    public BigDecimal getCode() {
       return this.code;
     }

    /**
     * Method 'setCode'.
     *
     * @param code BigDecimal
     */
    
    public void setCode(BigDecimal code) {
      this.code = code;
    }
    	
    /**
     * Method 'getProvincia'.
     *
     * @return Provincia
     */
    public Provincia getProvincia() {
       return this.provincia;
     }

    /**
     * Method 'setProvincia'.
     *
     * @param provincia Provincia
     */
    
    public void setProvincia(Provincia provincia) {
      this.provincia = provincia;
    }
    	
    /**
     * Method 'getDescEs'.
     *
     * @return String
     */
	 
    
    public String getDescEs() {
       return this.descEs;
     }

    /**
     * Method 'setDescEs'.
     *
     * @param descEs String
     */
    
    public void setDescEs(String descEs) {
      this.descEs = descEs;
    }
    	
    /**
     * Method 'getDescEu'.
     *
     * @return String
     */
	 
    
    public String getDescEu() {
       return this.descEu;
     }

    /**
     * Method 'setDescEu'.
     *
     * @param descEu String
     */
    
    public void setDescEu(String descEu) {
      this.descEu = descEu;
    }
    	
    /**
     * Method 'getCss'.
     *
     * @return String
     */
	 
    
    public String getCss() {
       return this.css;
     }

    /**
     * Method 'setCss'.
     *
     * @param css String
     */
    
    public void setCss(String css) {
      this.css = css;
    }
    	
    /**
     * Method 'getLocalidads'.
     *
     *
     * @return List
     */
	 @JsonIgnore
     public List<Localidad> getLocalidads() {
         return this.localidads;
     }
    /**
     * Method 'setLocalidads'.
     *
     * @param localidads List
     */
	 
    public void setLocalidads(List<Localidad> localidads) {
       this.localidads = localidads;
    }
	

/**
* Intended only for logging and debugging.
* 
* Here, the contents of every main field are placed into the result.
* @return String
*/
@Override
 public String toString() {
    StringBuilder result = new StringBuilder();
    result.append(this.getClass().getName()).append(" Object { " ); 
				result.append(" [ code: ").append(this.code).append(" ]");
				result.append(", [ descEs: ").append(this.descEs).append(" ]");
				result.append(", [ descEu: ").append(this.descEu).append(" ]");
				result.append(", [ css: ").append(this.css).append(" ]");
	result.append("}");
    return result.toString();
     }


}

